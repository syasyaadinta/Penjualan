package com.tes.Penjualan.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Helper {

    public static String formatRupiah(BigDecimal value){
        Locale indonesia = new Locale("id", "ID");
        String indoFormat = NumberFormat.getCurrencyInstance(indonesia).format(value);
        return indoFormat;
    }

}
