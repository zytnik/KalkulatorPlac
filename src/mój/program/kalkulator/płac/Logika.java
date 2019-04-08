package mój.program.kalkulator.płac;

public class Logika {

    public static double Z(double x) { //Zaokrąglanie do dwóch miejsc po przecinku
        return Math.round(x * 100.0) / 100.0;

    }

    public static WysokośćSkładekPLN ObliczNetto(double brutto) {
        //Koszty pracownika
        WysokośćSkładekPLN netto = new WysokośćSkładekPLN();
        WysokośćSkładekZUS2017 Składki = new WysokośćSkładekZUS2017();
        netto.KosztyUzyskania = Składki.PodstawoweKosztyUzyskania;
        netto.SkładkaEmerytalnaPracownika = Z(brutto * Składki.SkładkaEmerytalnaPracownika);
        netto.SkładkaRentowaPracownika = Z(brutto * Składki.SkładkaRentowaPracownika);
        netto.SkładkaChorobowa = Z(brutto * Składki.SkładkaChorobowa);
        netto.SkładkiSpołeczne = netto.SkładkaEmerytalnaPracownika + netto.SkładkaRentowaPracownika + netto.SkładkaChorobowa; //OK
        netto.PodstawaNaZdrowotne = brutto - netto.SkładkiSpołeczne;
        netto.SkładkaUbezpieczenieZdrowotne = Z(netto.PodstawaNaZdrowotne * Składki.SkładkaUbezpieczenieZdrowotne);
        netto.SkładkaZdrowotna = Z(netto.PodstawaNaZdrowotne * Składki.SkładkaZdrowotna);
        netto.Dochód = netto.PodstawaNaZdrowotne - netto.KosztyUzyskania;
        netto.ZaliczkaNaPodatekDochodowy = Math.round((netto.Dochód) * Składki.PierwszyPrógPodatku) - Składki.KwotaWolnaOdPodatku;
        netto.ZaliczkaPodatekDoZapłaty = Math.round(netto.ZaliczkaNaPodatekDochodowy - netto.SkładkaZdrowotna);
        netto.Netto = Z(brutto - netto.SkładkiSpołeczne - netto.SkładkaUbezpieczenieZdrowotne - netto.ZaliczkaPodatekDoZapłaty);

        // Koszy Pracowdawcy
        netto.SkładkaEmerytalnaPracodawcy = Z(brutto * Składki.SkładkaEmerytalnaPracodawcy);
        netto.SkładkaRentowaPracodawcy = Z(brutto * Składki.SkładkaRentowaPracodawcy);
        netto.SkładkaWypadkowaPracodawcy = Z(brutto * Składki.SkładkaWypadkowaPracodawcy);
        netto.SkładkaFunduszPracy = Z(brutto * Składki.SkładkaFunduszPracy);
        netto.SkładkaFGSP = Z(brutto * Składki.SkładkaFGSP);
        netto.KosztyPracodawcy = brutto + netto.SkładkaEmerytalnaPracodawcy + netto.SkładkaRentowaPracodawcy + netto.SkładkaWypadkowaPracodawcy + netto.SkładkaFunduszPracy + netto.SkładkaFGSP;
        netto.KosztyPracownika = netto.SkładkaEmerytalnaPracownika + netto.SkładkaRentowaPracownika + netto.SkładkaChorobowa + netto.SkładkaUbezpieczenieZdrowotne /*+ netto.ZaliczkaPodatekDoZapłaty*/;
        return netto;

    }

    public static Podatek Podatek(WysokośćSkładekPLN x) {

        WysokośćSkładekZUS2017 Składki = new WysokośćSkładekZUS2017();
        Podatek p = new Podatek();

        if (x.Dochód * 12 > Składki.PrógPodatku) {

            p.PierwszyPróg = Z((Składki.PrógPodatku * Składki.PierwszyPrógPodatku) - Składki.KwotaWolnaOdPodatku);
            p.DrugiPróg = Z((x.Dochód * 12 - Składki.PrógPodatku) * Składki.DrugiPrógPodatku);
            p.PodatekRoczny = Math.round(p.PierwszyPróg + p.DrugiPróg);

        } else {
            p.PodatekRoczny = Math.round(x.Dochód * 12 * Składki.PierwszyPrógPodatku) - Składki.KwotaWolnaOdPodatku;

        }

        return p;
    }

    public static String CzyDrugiPróg(WysokośćSkładekPLN x) {
        WysokośćSkładekZUS2017 Składki = new WysokośćSkładekZUS2017();
        String miesiąc = null;
        int i = 0;
        double z = x.Dochód;
        if (z * 12 < Składki.PrógPodatku) {
            miesiąc = "";
        } else {
            while (z < Składki.PrógPodatku) {
                z = z + x.Dochód;
                i++;
            }
            switch (i) {
                case 1:
                    miesiąc = "styczeń";
                    break;
                case 2:
                    miesiąc = "luty";
                    break;
                case 3:
                    miesiąc = "marzec";
                    break;
                case 4:
                    miesiąc = "kwiecień";
                    break;
                case 5:
                    miesiąc = "maj";
                    break;
                case 6:
                    miesiąc = "czerwiec";
                    break;
                case 7:
                    miesiąc = "lipiec";
                    break;
                case 8:
                    miesiąc = "sierpień";
                    break;
                case 9:
                    miesiąc = "wrzesień";
                    break;
                case 10:
                    miesiąc = "październik";
                    break;
                case 11:
                    miesiąc = "listopad";
                    break;
                case 12:
                    miesiąc = "grudzień";
                    break;
            }
        }
        return miesiąc;
    }

    public static boolean Podatek(double Dochód) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
