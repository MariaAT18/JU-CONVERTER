/*
 * Copyright (c) 2022 Jala University.
 * <p> * This software is the confidential and proprietary information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */

package com.jalasoft.convert.model.extractors.translatefiletxt;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/*
 * It is responsible for using the Google Translate API to translate a text document.
 * @author Sarai Alvarez
 * @version 1.0
 */
public class Gt_Translate {

    private static final String PATH = "https://translate.googleapis.com/translate_a/single"; // Direction
    private static final String CLIENT = "gtx";
    private static final String USER_AGENT = "Mozilla/5.0"; //"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
    private static final Map<String, String> LANGUAGE_MAP = new HashMap<>();
    public static String txt;

    private static Gt_Translate _instance = null;

    /*
     * get singleton
     * @return
     */
    public static Gt_Translate getInstance() {
        if (null == _instance) {
            _instance = new Gt_Translate();
            _instance.init();
        }
        return _instance;
    }

    /* Initialize the language class   */
    private void init() {
        LANGUAGE_MAP.put("auto", "Automatic");
        LANGUAGE_MAP.put("af", "Afrikaans");
        LANGUAGE_MAP.put("sq", "Albanian");
        LANGUAGE_MAP.put("am", "Amharic");
        LANGUAGE_MAP.put("ar", "Arabic");
        LANGUAGE_MAP.put("hy", "Armenian");
        LANGUAGE_MAP.put("az", "Azerbaijani");
        LANGUAGE_MAP.put("eu", "Basque");
        LANGUAGE_MAP.put("be", "Belarusian");
        LANGUAGE_MAP.put("bn", "Bengali");
        LANGUAGE_MAP.put("bs", "Bosnian");
        LANGUAGE_MAP.put("bg", "Bulgarian");
        LANGUAGE_MAP.put("ca", "Catalan");
        LANGUAGE_MAP.put("ceb", "Cebuano");
        LANGUAGE_MAP.put("ny", "Chichewa");
        LANGUAGE_MAP.put("zh_cn", "Chinese Simplified");
        LANGUAGE_MAP.put("zh_tw", "Chinese Traditional");
        LANGUAGE_MAP.put("co", "Corsican");
        LANGUAGE_MAP.put("hr", "Croatian");
        LANGUAGE_MAP.put("cs", "Czech");
        LANGUAGE_MAP.put("da", "Danish");
        LANGUAGE_MAP.put("nl", "Dutch");
        LANGUAGE_MAP.put("en", "English");
        LANGUAGE_MAP.put("eo", "Esperanto");
        LANGUAGE_MAP.put("et", "Estonian");
        LANGUAGE_MAP.put("tl", "Filipino");
        LANGUAGE_MAP.put("fi", "Finnish");
        LANGUAGE_MAP.put("fr", "French");
        LANGUAGE_MAP.put("fy", "Frisian");
        LANGUAGE_MAP.put("gl", "Galician");
        LANGUAGE_MAP.put("ka", "Georgian");
        LANGUAGE_MAP.put("de", "German");
        LANGUAGE_MAP.put("el", "Greek");
        LANGUAGE_MAP.put("gu", "Gujarati");
        LANGUAGE_MAP.put("ht", "Haitian Creole");
        LANGUAGE_MAP.put("ha", "Hausa");
        LANGUAGE_MAP.put("haw", "Hawaiian");
        LANGUAGE_MAP.put("iw", "Hebrew");
        LANGUAGE_MAP.put("hi", "Hindi");
        LANGUAGE_MAP.put("hmn", "Hmong");
        LANGUAGE_MAP.put("hu", "Hungarian");
        LANGUAGE_MAP.put("is", "Icelandic");
        LANGUAGE_MAP.put("ig", "Igbo");
        LANGUAGE_MAP.put("id", "Indonesian");
        LANGUAGE_MAP.put("ga", "Irish");
        LANGUAGE_MAP.put("it", "Italian");
        LANGUAGE_MAP.put("ja", "Japanese");
        LANGUAGE_MAP.put("jw", "Javanese");
        LANGUAGE_MAP.put("kn", "Kannada");
        LANGUAGE_MAP.put("kk", "Kazakh");
        LANGUAGE_MAP.put("km", "Khmer");
        LANGUAGE_MAP.put("ko", "Korean");
        LANGUAGE_MAP.put("ku", "Kurdish (Kurmanji)");
        LANGUAGE_MAP.put("ky", "Kyrgyz");
        LANGUAGE_MAP.put("lo", "Lao");
        LANGUAGE_MAP.put("la", "Latin");
        LANGUAGE_MAP.put("lv", "Latvian");
        LANGUAGE_MAP.put("lt", "Lithuanian");
        LANGUAGE_MAP.put("lb", "Luxembourgish");
        LANGUAGE_MAP.put("mk", "Macedonian");
        LANGUAGE_MAP.put("mg", "Malagasy");
        LANGUAGE_MAP.put("ms", "Malay");
        LANGUAGE_MAP.put("ml", "Malayalam");
        LANGUAGE_MAP.put("mt", "Maltese");
        LANGUAGE_MAP.put("mi", "Maori");
        LANGUAGE_MAP.put("mr", "Marathi");
        LANGUAGE_MAP.put("mn", "Mongolian");
        LANGUAGE_MAP.put("my", "Myanmar (Burmese)");
        LANGUAGE_MAP.put("ne", "Nepali");
        LANGUAGE_MAP.put("no", "Norwegian");
        LANGUAGE_MAP.put("ps", "Pashto");
        LANGUAGE_MAP.put("fa", "Persian");
        LANGUAGE_MAP.put("pl", "Polish");
        LANGUAGE_MAP.put("pt", "Portuguese");
        LANGUAGE_MAP.put("ma", "Punjabi");
        LANGUAGE_MAP.put("ro", "Romanian");
        LANGUAGE_MAP.put("ru", "Russian");
        LANGUAGE_MAP.put("sm", "Samoan");
        LANGUAGE_MAP.put("gd", "Scots Gaelic");
        LANGUAGE_MAP.put("sr", "Serbian");
        LANGUAGE_MAP.put("st", "Sesotho");
        LANGUAGE_MAP.put("sn", "Shona");
        LANGUAGE_MAP.put("sd", "Sindhi");
        LANGUAGE_MAP.put("si", "Sinhala");
        LANGUAGE_MAP.put("sk", "Slovak");
        LANGUAGE_MAP.put("sl", "Slovenian");
        LANGUAGE_MAP.put("so", "Somali");
        LANGUAGE_MAP.put("es", "Spanish");
        LANGUAGE_MAP.put("su", "Sundanese");
        LANGUAGE_MAP.put("sw", "Swahili");
        LANGUAGE_MAP.put("sv", "Swedish");
        LANGUAGE_MAP.put("tg", "Tajik");
        LANGUAGE_MAP.put("ta", "Tamil");
        LANGUAGE_MAP.put("te", "Telugu");
        LANGUAGE_MAP.put("th", "Thai");
        LANGUAGE_MAP.put("tr", "Turkish");
        LANGUAGE_MAP.put("uk", "Ukrainian");
        LANGUAGE_MAP.put("ur", "Urdu");
        LANGUAGE_MAP.put("uz", "Uzbek");
        LANGUAGE_MAP.put("vi", "Vietnamese");
        LANGUAGE_MAP.put("cy", "Welsh");
        LANGUAGE_MAP.put("xh", "Xhosa");
        LANGUAGE_MAP.put("yi", "Yiddish");
        LANGUAGE_MAP.put("yo", "Yoruba");
        LANGUAGE_MAP.put("zu", "Zulu");
    }

    /*
     * Determine if the language supports
     * @param language
     * @return
     */

    // if support language translation or not
    public boolean isSupport(String language) {
        if (null == LANGUAGE_MAP.get(language)) {
            return false;
        }
        return true;
    }

    /*
     * Get the language code
     * ISO 639-1 code
     * @param desired language language
     * @return If it returns null, it indicates that it is not supported.
     */
    public String getCode(String desiredLang) {
        if (null != LANGUAGE_MAP.get(desiredLang)) {
            return desiredLang;
        }
        String tmp = desiredLang.toLowerCase();
        for (Map.Entry<String, String> enter : LANGUAGE_MAP.entrySet()) {
            if (enter.getValue().equals(tmp)) {
                return enter.getKey();
            }
        }
        return null;
    }

    /*
     * Translated text
     * @param text text content
     * @param sourceLang The language of the text. If you don't know, you can use auto
     * @param targetLang target language. Must be a clear and effective target language.
     * @return
     * @throws Exception
     */

    public String translateText(String text, String sourceLang, String targetLang) {
        String retStr = "";
        /*
        if( !( isSupport(sourceLang) || isSupport(targetLang) ) ){
            //unir con el logger de Agriana Olivera para lanzar la excepcion
                         //lanzar una nueva excepción ("Tipo de idioma no admitido");
        }*/

        List<NameValuePair> nvps = new ArrayList();
        nvps.add(new BasicNameValuePair("client", CLIENT));
        nvps.add(new BasicNameValuePair("sl", sourceLang));
        nvps.add(new BasicNameValuePair("tl", targetLang));
        nvps.add(new BasicNameValuePair("dt", "t"));
        nvps.add(new BasicNameValuePair("q", text));
//        String finalPath=PATH +"?client="+CLIENT+"&sl="+sourceLang+"&tl="+targetLang+"&dt=t&q="+ text ;

        String resp = postHttp(PATH, nvps);

        /*
        if( null == resp ){//in case you have no connection to the server.
            //unir con el logger de adri para lanzar la excepcion
                        //lanzar una nueva excepción ("Excepción de red");
        } */

        // System.out.println ("==> Devolver contenido:" + resp);

        JSONArray jsonObject = JSONArray.parseArray(resp);
        for (Iterator<Object> it = jsonObject.getJSONArray(0).iterator(); it.hasNext(); ) {
            JSONArray a = (JSONArray) it.next();
            retStr += a.getString(0);
        }
        return retStr;
    }

    /*
     * post request
     * @param url request address
     * @param nvps parameter list
     * @return
     * @throws UnsupportedEncodingException
     */
    private String postHttp(String url, List<NameValuePair> nvps) {
        String responseStr = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //important! ! The http header must be set; otherwise the return is distorted
        httpPost.setHeader("User-Agent", USER_AGENT);
        CloseableHttpResponse response2 = null;
        try {
            // Important! ! Specify the encoding to encode Chinese
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
            response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            responseStr = EntityUtils.toString(entity2);
            EntityUtils.consume(entity2);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response2) {
                try {
                    response2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }
}
