package Kalkulator.testy;

import mój.program.kalkulator.płac.Logika;

public class MójProgramKalkulatorPłac {

    public static void main(String[] args) {
        double x = 2000;
        System.out.println(Logika.ObliczNetto(x).SkładkiSpołeczne);
        System.out.println(Logika.ObliczNetto(x).KosztyPracodawcy);
        System.out.println(Logika.ObliczNetto(x).KosztyPracownika);
        System.out.println(Logika.ObliczNetto(x).ZaliczkaPodatekDoZapłaty);
        System.out.println(Logika.ObliczNetto(x).Netto);
        System.out.println(Logika.ObliczNetto(x).KosztyPracodawcy);
        System.out.println(Logika.ObliczNetto(x).KosztyPracownika);
        System.out.println(Logika.Podatek(Logika.ObliczNetto(x)).PodatekRoczny);
        System.out.println(Logika.Podatek(Logika.ObliczNetto(x)).PodatekRoczny + (12 * Logika.ObliczNetto(x).KosztyPracownika));
        System.out.println(Logika.Podatek(Logika.ObliczNetto(x).Dochód));
       // System.out.println(Logika.Podatek(Logika.ObliczNetto(x)).PierwszyPróg);
       // System.out.println(Logika.Podatek(Logika.ObliczNetto(x)).DrugiPróg);

    }

}
