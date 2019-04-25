package sdlab2200.de_xian.bbp.com.sdlab2200;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sdlab2200.de_xian.bbp.com.sdlab2200.database.DatabaseHandler;
import sdlab2200.de_xian.bbp.com.sdlab2200.database.History;

public class ActivityEncryptDecrypt extends AppCompatActivity {

    private Button btn_encrypt;
    private Button btn_decrypt;
    private EditText et_msgClear;
    private EditText et_msgEncrypted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_decrypt);

        //TESTING

        final DatabaseHandler DB = new DatabaseHandler(getApplicationContext());

        //TESTING END

        btn_encrypt = findViewById(R.id.btn_encrypt);
        btn_decrypt = findViewById(R.id.btn_decrypt);
        et_msgClear = findViewById(R.id.et_msgClear);
        et_msgEncrypted = findViewById(R.id.et_msgEncrypted);


        btn_encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = et_msgClear.getText().toString();

                try {
                    //Log.i("XIAN",""+encrypt(text,"liya"));
                    et_msgEncrypted.setText(simpleEncryption(text));
                    et_msgClear.setText("");

                    String dateTime = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
                    dateTime = converDateTime(dateTime);
                    History h = new History(dateTime,"Encrypted Result :"+simpleEncryption(text));
                    DB.addHistory(h);

                } catch (Exception e) {
                    Log.i("XIAN",""+e);
                }

            }
        });

        btn_decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = et_msgEncrypted.getText().toString();

                try {
                    et_msgClear.setText(simpleDecryption(text));
                    et_msgEncrypted.setText("");

                    String dateTime = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
                    dateTime = converDateTime(dateTime);
                    History h = new History(dateTime,"Decrypted Result :"+simpleDecryption(text));
                    DB.addHistory(h);
                } catch (Exception e) {
                    Log.i("XIAN",""+e);
                }

            }
        });

    }


    public static String encrypt(String strClearText,String strKey) throws Exception{
        String strData="";

        try {
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted = cipher.doFinal(strClearText.getBytes());
            strData=new String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }

    public static String decrypt(String strEncrypted,String strKey) throws Exception{
        String strData="";

        try {
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted = cipher.doFinal(strEncrypted.getBytes());
            strData=new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }

    public static String simpleEncryption(String x){
        String Newstr = "";
        for (int i=0;i<x.length();i++){
            char ch=Character.toLowerCase(x.charAt(i));
            switch (ch){

                case 'a':
                    Newstr=Newstr+"{";
                    break;
                case 'b':
                    Newstr=Newstr+"}";
                    break;
                case 'c':
                    Newstr=Newstr+"#";
                    break;
                case 'd':
                    Newstr=Newstr+"~";
                    break;
                case 'e':
                    Newstr=Newstr+"+";
                    break;
                case 'f':
                    Newstr=Newstr+"-";
                    break;
                case 'g':
                    Newstr=Newstr+"*";
                    break;
                case 'h':
                    Newstr=Newstr+"@";
                    break;
                case 'i':
                    Newstr=Newstr+"/";
                    break;
                case 'j':
                    Newstr=Newstr+"\\";
                    break;
                case 'k':
                    Newstr=Newstr+"?";
                    break;
                case 'l':
                    Newstr=Newstr+"$";
                    break;
                case 'm':
                    Newstr=Newstr+"!";
                    break;
                case 'n':
                    Newstr=Newstr+"^";
                    break;
                case 'o':
                    Newstr=Newstr+"(";
                    break;
                case 'p':
                    Newstr=Newstr+")";
                    break;
                case 'q':
                    Newstr=Newstr+"<";
                    break;
                case 'r':
                    Newstr=Newstr+">";
                    break;
                case 's' :
                    Newstr=Newstr+"=";
                    break;
                case 't':
                    Newstr=Newstr+";";
                    break;
                case 'u':
                    Newstr=Newstr+",";
                    break;
                case 'v' :
                    Newstr=Newstr+"_";
                    break;
                case 'w':
                    Newstr=Newstr+"[";
                    break;
                case 'x' :
                    Newstr=Newstr+"]";
                    break;
                case 'y':
                    Newstr=Newstr+":";
                    break;
                case 'z' :
                    Newstr=Newstr+"\"";
                    break;
                case ' ' :
                    Newstr=Newstr+" ";
                    break;
                case '.':
                    Newstr=Newstr+'3';
                    break;
                case ',':
                    Newstr=Newstr+"1";
                    break;
                case '(':
                    Newstr=Newstr+'4';
                    break;
                case '\"':
                    Newstr=Newstr+'5';
                    break;
                case ')' :
                    Newstr=Newstr+"7";
                    break;
                case '?' :
                    Newstr= Newstr+"2";
                    break;
                case '!':
                    Newstr= Newstr+"8";
                    break;
                case '-' :
                    Newstr= Newstr+"6";
                    break;
                case '%' :
                    Newstr = Newstr+"9";
                    break;
                case '1':
                    Newstr=Newstr+"r";
                    break;
                case '2':
                    Newstr=Newstr+"k";
                    break;
                case '3':
                    Newstr=Newstr+"b";
                    break;
                case '4':
                    Newstr = Newstr+"e";
                    break;
                case '5':
                    Newstr = Newstr+"q";
                    break;
                case '6':
                    Newstr = Newstr+"h";
                    break;
                case '7':
                    Newstr = Newstr+"u";
                    break;
                case '8' :
                    Newstr= Newstr+"y";
                    break;
                case '9':
                    Newstr = Newstr+"w";
                    break;
                case '0':
                    Newstr = Newstr+"z";
                    break;
                default:
                    Newstr=Newstr+" ";
                    break;
            }
        }

        return Newstr;
    }

    public static String simpleDecryption(String x){
        String Newstr = "";
        for (int i=0;i<x.length();i++)
        {
            char ch=Character.toLowerCase(x.charAt(i));
            switch (ch){
                case '{':
                    Newstr=Newstr+"A";
                    break;
                case '}':
                    Newstr=Newstr+"B";
                    break;
                case '#':
                    Newstr=Newstr+"C";
                    break;
                case '~':
                    Newstr=Newstr+"D";
                    break;
                case '+':
                    Newstr=Newstr+"E";
                    break;
                case '-':
                    Newstr=Newstr+"F";
                    break;
                case '*':
                    Newstr=Newstr+"G";
                    break;
                case '@':
                    Newstr=Newstr+"H";
                    break;
                case '/':
                    Newstr=Newstr+"I";
                    break;
                case '\\':
                    Newstr=Newstr+"J";
                    break;
                case '?':
                    Newstr=Newstr+"K";
                    break;
                case '$':
                    Newstr=Newstr+"L";
                    break;
                case '!':
                    Newstr=Newstr+"M";
                    break;
                case '^':
                    Newstr=Newstr+"N";
                    break;
                case '(':
                    Newstr=Newstr+"O";
                    break;
                case ')':
                    Newstr=Newstr+"P";
                    break;
                case '<':
                    Newstr=Newstr+"Q";
                    break;
                case '>':
                    Newstr=Newstr+"R";
                    break;
                case '=' :
                    Newstr=Newstr+"S";
                    break;
                case ';':
                    Newstr=Newstr+"T";
                    break;
                case ',':
                    Newstr=Newstr+"U";
                    break;
                case '_' :
                    Newstr=Newstr+"V";
                    break;
                case '[':
                    Newstr=Newstr+"W";
                    break;
                case ']' :
                    Newstr=Newstr+"X";
                    break;
                case ':':
                    Newstr=Newstr+"Y";
                    break;
                case '\"' :
                    Newstr=Newstr+"Z";
                    break;
                case '1':
                    Newstr=Newstr+"r";
                    break;
                case '2':
                    Newstr=Newstr+"k";
                    break;
                case '3':
                    Newstr=Newstr+"b";
                    break;
                case '4':
                    Newstr = Newstr+"e";
                    break;
                case '5':
                    Newstr = Newstr+"q";
                    break;
                case '6':
                    Newstr = Newstr+"h";
                    break;
                case '7':
                    Newstr = Newstr+"u";
                    break;
                case '8' :
                    Newstr= Newstr+"y";
                    break;
                case '9':
                    Newstr = Newstr+"w";
                    break;
                case '0':
                    Newstr = Newstr+"z";
                    break;
                default:
                    Newstr=Newstr+" ";
                    break;
            }
        }
        return Newstr;
    }

    private String converDateTime(String dt) {

        String dateTime = "";
        //01 23 4567 8 910 1112 1314
        dateTime+= "Time: "+dt.substring(9,11)+":"+dt.substring(11,13);
        dateTime+= " Date: "+dt.substring(0,2)+"/"+dt.substring(2,4)+"/"+dt.substring(4,8);

        return dateTime;

    }


}
