package com.padc.beauty.data.models;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.PerDetailVO;
import com.padc.beauty.data.vos.PersonalityDetailVO;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.utils.CommonInstances;
import com.padc.beauty.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aung on 6/24/16.
 */
public class PersonalityDetailModel {

    private static final String DUMMY_EVENT_LIST = "personalitydetail_list.json";

    private static PersonalityDetailModel objInstance;

    private List<PersonalityDetailVO> personalityList;

    private PersonalityDetailModel() {
        personalityList = setUpInitialPersonalities();
    }

    public static PersonalityDetailModel getInstance() {
        if (objInstance == null) {
            objInstance = new PersonalityDetailModel();
        }

        return objInstance;
    }

    private List<PersonalityDetailVO> setUpInitialPersonalities() {
//        Context context = BeautyApp.getContext();
//
//        List<PersonalityDetailVO> personalitiesList = new ArrayList<>();
//        personalitiesList.add(new PersonalityDetailVO(context.getString(R.string.personality_one_title), context.getString(R.string.personality_one_content), R.drawable.develop_a_good_personality_step1));
//        personalitiesList.add(new PersonalityDetailVO(context.getString(R.string.personality_two_title), context.getString(R.string.personality_two_content), R.drawable.develop_a_good_personality_step5));
//        personalitiesList.add(new PersonalityDetailVO(context.getString(R.string.personality_three_title), context.getString(R.string.personality_three_content), R.drawable.develop_a_good_personality_step9));
//        return personalitiesList;
        List<PersonalityDetailVO> personalityList = new ArrayList<>();

        try {
            String dummyEventList = JsonUtils.getInstance().loadDummyData(DUMMY_EVENT_LIST);
            Type listType = new TypeToken<List<PersonalityDetailVO>>() {
            }.getType();
            personalityList = CommonInstances.getGsonInstance().fromJson(dummyEventList, listType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return personalityList;
    }

    public List<PersonalityDetailVO> getPersonalityList() {
        return personalityList;
    }

    public PersonalityDetailVO getPersonalityDetail(int index) {
        return personalityList.get(index);
    }

    public PersonalityDetailVO getPersonalityByID(long tipid) {

        for (PersonalityDetailVO personality : personalityList) {
            Log.d(BeautyApp.TAG,"personalityid"+personality.getTipid());
            if (personality.getTipid()== tipid) {
                return personality;
            }
            else
            {
                return personality;
            }
        }
       return null;
    }

    public void addFavorite(PersonalityDetailVO personalitydtl,Integer index) {
        List<PerDetailVO> perdtllist=personalitydtl.getPersondtlVO();
        BookmarkVO bookmarkvo=new BookmarkVO(personalitydtl.getTipid(),perdtllist.get(index).getPersonalityTitle(),perdtllist.get(index).getPersonalityImage(),perdtllist.get(index).getPersonalityContent());
        List<BookmarkVO> currentbookmarklist=new ArrayList<>();
        currentbookmarklist.add(bookmarkvo);
        BookMarkModel.getInstance().notifyBookMarkLoaded(currentbookmarklist);

    }
}