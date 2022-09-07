package pers.juumii.antiaddiction.model.environment.environment.cptenviroment;

import com.google.gson.JsonElement;

import java.net.MalformedURLException;
import java.net.URL;

public class WebsiteBrowsingData implements ComputerEnvironmentData{

    public final String className = getClass().getName();

    public URL url;

    public WebsiteBrowsingData(){
        try {
            this.url = new URL("https://www.bilibili.com/video/BV14u411d7BN?spm_id_from=333.337.search-card.all.click");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    public WebsiteBrowsingData(URL url){
        this.url = url;
    }
    public WebsiteBrowsingData(JsonElement json) throws MalformedURLException {
        this.url = new URL(json.getAsJsonObject().get("url").getAsString());
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public int getIdCode() {
        return url.hashCode();
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
