package edu.uoc.pac1;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PAC1Ex2 {

    static DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("EN"));
    static DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);

    public static final double RATE_FIRST_YEAR = 1.95;

    private static double power(double base, int exponent){
        if (exponent > 0) return power(base, exponent - 1) * base;
        else if (exponent < 0) return power(base, exponent + 1) / base;
        else return 1; //Base case
    }

    //////////////////////////////////////////

    public static double mortgageMonthlyFee(double capital, double annualInterestRate, int months) {
        //TODO
        if (capital <=0 || annualInterestRate <=0 || months<=0){return -1;};

        double cuota = (capital*(annualInterestRate/(100*12))*power(1+(annualInterestRate/(100*12)),months))/
                ((power(1+(annualInterestRate/(100*12)),months))-1);
        return cuota;
    }

    /*  *********    Funcion redondeaYformatea(double importe)     *********

        Recibe una cantidad (double) como argumento y la retorna en forma de String
        con dos decimales y separador de punto "."

     */
    public static String redondeaYformatea(double importe){

        DecimalFormat df = new DecimalFormat("0.00");
        importe *= 1000000; importe = Math.round(importe); importe/=1000000;
        String redondeo = df.format(Double.parseDouble(String.valueOf(importe)));
        redondeo= redondeo.replace(',','.');
        return redondeo;

    }




    public static double mortgageAmortizationSchedule(double capital, int years, double annualInterestRate){
        //TODO
        if(years<2){
            System.out.println("The duration of the mortgage must be equal to or greater than two years");
            return -1;
        };
        if(annualInterestRate>=(double)1.95){
            System.out.println("The interest applied must be less than the fixed interest of the first year");
            return -1;
        };

        double sumaIntereses=0;
        double deuda = capital;

        for(int i = 1; i<=12; i++){

            double cuota = (PAC1Ex2.mortgageMonthlyFee(capital,1.95,years*12));
            double intereses = deuda*(1.95/1200);
            double amortizacion = cuota - intereses;
            System.out.printf("Month: "+i+", "+"Fee: "+ redondeaYformatea(cuota)+ ", ");
            System.out.printf("Interest: "+ redondeaYformatea(intereses));
            System.out.printf(", Amortization: "+redondeaYformatea(amortizacion));
            deuda -= amortizacion;
            System.out.printf(", Debt: " +redondeaYformatea(deuda)+"\n");
            sumaIntereses+=intereses;
        }
        capital=deuda; //Actualizo capital con el valor actual de la deuda para volver a calcular la cuota a pagar a partir del segundo año.

        for(int i = 13; i<=(years*12); i++){

            double cuota = PAC1Ex2.mortgageMonthlyFee(capital,annualInterestRate,(years-1)*12);
            double intereses = deuda*(annualInterestRate/1200);
            double amortizacion = cuota - intereses;
            System.out.printf("Month: "+i+", "+"Fee: "+ redondeaYformatea(cuota)+ ", ");
            System.out.printf("Interest: "+ redondeaYformatea(intereses));
            System.out.printf(", Amortization: "+redondeaYformatea(amortizacion));
            deuda -= amortizacion;
            System.out.printf(", Debt: " +redondeaYformatea(deuda)+"\n");
            sumaIntereses+=intereses;


        }

        return sumaIntereses;

    }


    public static double fixedRate(double capital){
        //TODO
        if(capital<=100000){return 1.06;}
        if(capital<=150000){return 1.15;}
        return 1.25;

    }

    public static double mortgageAmortizationSchedule(double capital, int years){
        //TODO
        if(years < 2){
            System.out.println("The duration of the mortgage must be equal to or greater than two years");
            return -1;
        }

        double interes2 = (double)PAC1Ex2.fixedRate(capital); // Calculamos el interes en funcion del capital.

        double respuesta = mortgageAmortizationSchedule( capital, years,interes2); // Uso al funcion codificada anteriormente

        return  respuesta;
    }

}