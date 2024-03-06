package com.example.ecommerce.main.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.ecommerce.main.repository.models.localdata.AddressData;
import com.example.ecommerce.main.repository.models.localdata.Cart;
import com.example.ecommerce.main.repository.models.localdata.CreditCard;
import com.example.ecommerce.main.repository.models.localdata.Favourites;
import com.example.ecommerce.main.repository.models.localdata.Order;
import com.example.ecommerce.main.repository.models.localdata.UserExtras;
import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.repository.models.remotedata.Users;
import com.example.ecommerce.main.ui.Address;
import com.example.ecommerce.main.ui.LoginScreen;
import com.example.ecommerce.main.ui.Mainscreen;
import com.example.ecommerce.main.ui.Payment;
import com.example.ecommerce.main.ui.SuccessLogin;
import com.example.ecommerce.main.ui.UserProfile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static int emaillogin=2;
    public  static int passwordlogin=2;
    public  static boolean flag=false;
    public static UserExtras Activeuser;
public static Products Universalproduct;
    public static ArrayList<Cart> temp=new ArrayList<Cart>();
    public static ArrayList<Favourites> favList=new ArrayList<>();
    public static AddressData addressData;
    public static CreditCard creditCard;
    private static float totalprice=0;
    private static int totalquantity=0;
    public  static  String titles;
    private  static int orderindex=0;
  public  static  ArrayList<Order> orders=new ArrayList<>();

    public static ArrayList<Order> getOrders() {
        orders.clear();
        retrieveordrer();
        return orders;
    }

    public static void AddOrders(Order nwordr) {
        titles="";
        for (int i=0;i<nwordr.getCart().size();i++)
        {
            String temp=nwordr.getCart().get(i).getTitle();
          titles=titles.concat("\n"+temp);

        }
        orderindex+=1;
        nwordr.setPrice(Float.toString( totalprice));
        nwordr.setIndex(orderindex);
        nwordr.setTitles(titles);
        orders.add(nwordr);
    }
    public static void updateorder()
    {
        Gson gson = new Gson();
        String json = gson.toJson(orders);
        Mainscreen.editor.putString(Mainscreen.order, json);
        Mainscreen. editor.apply();

    }
    public  static void retrieveordrer()

    {
        String check=Mainscreen.sharedPreferences.getString(Mainscreen.order,null);
        if(!(check==null)) {
        Gson gson = new Gson();
        String json = Mainscreen.sharedPreferences.getString(Mainscreen.order, "");
        Type userListType = new TypeToken<ArrayList<Order>>() {}.getType();
        orders = gson.fromJson(json, userListType);
        orderindex = (orders.get(orders.size() - 1).getIndex()) ;
    }

    }


    //returns total quantity of all products placed in the cart
    public static int getTotalquantity() {
        return totalquantity;
    }
//sets total quantity of all products placed in the cart
    public static void setTotalquantity(int totalquantity) {
        Helper.totalquantity = totalquantity;
    }

    //get the static data total price used in cart and checkout screens
    public static float getTotalprice() {
        return totalprice;
    }

    public static void setTotalprice(float totalprice) {
        Helper.totalprice = totalprice;
    }
    //used to add the active user details fetched from api to the shared prefrences data
    public static void setuser()
    {

      String name= Mainscreen.sharedPreferences.getString(Mainscreen.name,"name");
        String email= Mainscreen.sharedPreferences.getString(Mainscreen.emailuser,"emailuser");
       int id =Integer.parseInt( Mainscreen.sharedPreferences.getString(Mainscreen.id,"id"));
        String role = Mainscreen.sharedPreferences.getString(Mainscreen.role,"role");
        String avatar = Mainscreen.sharedPreferences.getString(Mainscreen.avatar,"avatar");

        Activeuser =new UserExtras(id,email,name,role,avatar);

    }

    /*used to add the active user address details from the shared prefrences data to the
    static object assiggned to runtime of program
    */

    public static void setaddress()

    {   String check=Mainscreen.sharedPreferences.getString(Mainscreen.street,null);

        if(!(check==null)){
        String street=Mainscreen.sharedPreferences.getString(Mainscreen.street,"");
        String state=Mainscreen.sharedPreferences.getString(Mainscreen.addressstate,"");
        String city=Mainscreen.sharedPreferences.getString(Mainscreen.city,"");
        String number=Mainscreen.sharedPreferences.getString(Mainscreen.phonenumber,"");
        String zip=Mainscreen.sharedPreferences.getString(Mainscreen.zipcode,"");
        String country=Mainscreen.sharedPreferences.getString(Mainscreen.country,"");
        addressData=new AddressData(street,city,state,number,zip,country);
        Activeuser.setAddressData(addressData);
    }

    }
    public static void setCard()
    {
        String check=Mainscreen.sharedPreferences.getString(Mainscreen.card,null);
        if(!(check==null))
        {  String cardnumber=Mainscreen.sharedPreferences.getString(Mainscreen.card,"");
            String expire=Mainscreen.sharedPreferences.getString(Mainscreen.expire,"");
            String cvv=Mainscreen.sharedPreferences.getString(Mainscreen.cvv,"");
            creditCard=new CreditCard(cardnumber,expire,cvv);
            Activeuser.setCard(creditCard);
        }

    }

    /*takes 4 parameters enterd password and email and list of users on the api and makes an operation on them
        to check if the email and pass are correct or not
    */
    public static int loginchecker(List<Users> data, String email, String pass, Context ctx){


        for(int i=0;i<data.size();i++) {
            if (data.get(i).getEmail().equals(email)) {
                emaillogin = 0;
                passwordlogin=1;
                if (data.get(i).getPassword().equals(pass)) {
                    passwordlogin = 0;
                    Log.i("helper", "loginchecker: ");
                    Intent intent = new Intent(ctx, SuccessLogin.class);
                    flag=true;
               Activeuser=new UserExtras(data.get(i).getId(),data.get(i).getEmail(),data.get(i).getName(),data.get(i).getRole(),data.get(i).getAvatar());
                    ctx.startActivity(intent);
                   return 1;

                }
            }
        }
        if(flag==false)
        {
            emaillogin=1;
            passwordlogin=1;
        }
return 0;



    }
    public  ProgressDialog myProgressDialog(Context ctx) {
        ProgressDialog dialog = new ProgressDialog(ctx);
        dialog.setTitle("Fetching API");
        dialog.setMessage("Loading Data");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);

        return dialog;
    }
    public AlertDialog myAlertDialog(Context ctx,String title,String message,Context target)
    {
       AlertDialog dialog= new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                  Intent intent=new Intent(ctx, target.getClass());
                     dialog.dismiss();
                        ctx.startActivity(intent);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


   return dialog; }
    public  static ArrayList<Cart> GetCart()
    {
        int count=Mainscreen.dbHelper.getAllCart();

            temp=Mainscreen.dbHelper.getAllItems(String.valueOf(1),String.valueOf(1));

        return temp;
    }

    public  static ArrayList<Favourites> Getfav()
    {
        int count=Mainscreen.favHelper.getAllFav();

        favList=Mainscreen.favHelper.getAllItems(String.valueOf(1),String.valueOf(1));

        return favList;
    }

    public static String replaceLastFour(String s) {
        int length = s.length();
        //Check whether or not the string contains at least four characters; if not, this method is useless
        if (length < 4) return "Error: The provided string is not greater than four characters long.";
        return s.substring(0, length - 4) + "****";
    }
            }
