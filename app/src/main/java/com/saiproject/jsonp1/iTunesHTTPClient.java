package com.saiproject.jsonp1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** This class does two things
 * 1. The method getItunesStuffData takes the BASE_URL and the JSON object generated by clicking on it is stored in a StringBuffer
 * which is ultimately returned
 *
 * 2. The method getBitmapFromURL returns bitmap image of the Album
 *
 *
 *
     **/



public class iTunesHTTPClient {



    private static String BASE_URL = "https://itunes.apple.com/search?term=jack+johnson";


    /* This method returns the entire JSON object data in string format */
    public String getITunesStuffData() {


        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;


        try {

            // Setup httpURLConnection

            httpURLConnection = (HttpURLConnection) (new URL(BASE_URL)).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true); //Use information as input
//            httpURLConnection.setDoOutput(true); //To upload in internet (not necessary)
            httpURLConnection.connect();






            //Read response
            StringBuffer stringBuffer = new StringBuffer(); //String Buffer is a mutable string
            inputStream = httpURLConnection.getInputStream();   //Get inputstream from HTTP URL
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //Create buffer using bufferedReader
            String line = null;
            //Read line by line from bufferedReader
            while ((line = bufferedReader.readLine()) != null) {

                stringBuffer.append(line + "\n");
            }

            inputStream.close();
            httpURLConnection.disconnect();

            return stringBuffer.toString();  //This marks the end of the success

        }


        catch (Throwable t) {
            t.printStackTrace();
        }




        finally {       //In case try fails finally is executed


            try {
                //Close the inputstream and HTTPURLConnection


                inputStream.close();
                httpURLConnection.disconnect();

            } catch (Throwable t) {
                t.printStackTrace();
            }
        }


        return null;   //This means our application has failed


        }


        /* This method returns the bitmap from the URL */

        public Bitmap getBitmapFromURL(String stringUrl){


        Bitmap bm= null;

        try{

            URL url = new URL(stringUrl);       // Get URL
            InputStream inputStream = url.openStream(); //To download image form internet
            bm = BitmapFactory.decodeStream(inputStream);

        }

        catch (IOException e){
            e.printStackTrace();
            return null;
        }

        return  bm;

        }


    }






