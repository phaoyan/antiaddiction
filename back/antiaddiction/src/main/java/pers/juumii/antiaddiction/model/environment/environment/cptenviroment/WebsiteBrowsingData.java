package pers.juumii.antiaddiction.model.environment.environment.cptenviroment;

import com.google.gson.JsonElement;

import java.net.MalformedURLException;
import java.net.URL;

public class WebsiteBrowsingData implements ComputerEnvironmentData{

    public final String className = getClass().getName();

    private URL url;
    @SuppressWarnings("all")
    private String urlWithoutParams;

    public WebsiteBrowsingData(){}
    public WebsiteBrowsingData(URL url){
        setUrl(url);
    }
    public WebsiteBrowsingData(JsonElement json) throws MalformedURLException {
        if(json.getAsJsonObject().get("url") == null)
            return;

        setUrl(new URL(json.getAsJsonObject().get("url").getAsString()));
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
        this.urlWithoutParams = url.toString().split("\\?")[0];
    }


    @Override
    public int getIdCode() {
        if(url == null)
            return -1;
        return url.toString().hashCode();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof WebsiteBrowsingData && ((WebsiteBrowsingData)obj).getIdCode() == this.getIdCode();
    }
}
