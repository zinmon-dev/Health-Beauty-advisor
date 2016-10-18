package com.padc.beauty.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by windows on 10/1/2016.
 */
public class PerDetailVO {
    @SerializedName("title")
    private String PersonalityTitle;

    @SerializedName("photo")
    private String PersonalityImage;

    @SerializedName("description")
    private String PersonalityContent;

    public String getPersonalityTitle() {
        return PersonalityTitle;
    }

    public String getPersonalityImage() {
        return PersonalityImage;
    }

    public String getPersonalityContent() {
        return PersonalityContent;
    }

    public void setPersonalityTitle(String personalityTitle) {
        PersonalityTitle = personalityTitle;
    }

    public void setPersonalityImage(String personalityImage) {
        PersonalityImage = personalityImage;
    }

    public void setPersonalityContent(String personalityContent) {
        PersonalityContent = personalityContent;
    }
}
