package test.openerp.utils;

import android.support.design.widget.Snackbar;
import android.view.View;



public class Toaster {


    public static void displaySnackbar(View view, String message){

        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_LONG);
        snackbar.show();

    }
}
