package com.example.gurufinalproject.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.bean.NoteBean;
import com.example.gurufinalproject.bean.NoticeBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FileDB {
    private static final String FILE_DB = "FileDB";
    private static Gson mGson = new Gson();

    private static SharedPreferences getSP(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_DB, Context.MODE_PRIVATE);
        return sp;
    }

    /** 새로운 멤버추가 */
    public static void addMember(Context context, MemberBean memberBean){
        //1.기존의 멤버 리스트를 불러온다.
        List<MemberBean> memberList = getMemberList(context);
        //2.기존의 멤버 리스트에 추가한다.
        memberList.add(memberBean);
        //3.멤버 리스트를 저장한다.
        String listStr = mGson.toJson(memberList);
        //4.저장한다.
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString("memberList", listStr);
        editor.commit();
    }

    public static List<MemberBean> getMemberList(Context context) {
        String listStr = getSP(context).getString("memberList", null);
        //저장된 리스트가 없을 경우에 새로운 리스트를 리턴한다.
        if(listStr == null) {
            return new ArrayList<MemberBean>();
        }
        //Gson 으로 변환한다.
        List<MemberBean> memberList =
                mGson.fromJson(listStr, new TypeToken<List<MemberBean>>(){}.getType() );
        return memberList;
    }


    public static MemberBean getFindMember(Context context, String userId) {
        //1.멤버리스트를 가져온다
        List<MemberBean> memberList = getMemberList(context);
        //2.for 문 돌면서 해당 아이디를 찾는다.
        for(MemberBean bean : memberList) {
            if(TextUtils.equals(bean.userid, userId)) { //아이디가 같다.
                //3.찾았을 경우는 해당 MemberBean 을 리턴한다.
                return bean;
            }
        }
        //3-2.못찾았을 경우는??? null 리턴
        return null;
    }

    /** 새로운 멤버추가 */
    public static void addAdmin(Context context, MemberBean memberBean){
        //1.기존의 멤버 리스트를 불러온다.
        List<MemberBean> memberList = getAdminList(context);
        //2.기존의 멤버 리스트에 추가한다.
        memberList.add(memberBean);
        //3.멤버 리스트를 저장한다.
        String listStr = mGson.toJson(memberList);
        //4.저장한다.
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString("adminList", listStr);
        editor.commit();
    }

    //기존 멤버 교체
    public static void setAdmin(Context context, MemberBean memberBean) {
        //전체 멤버 리스트를 취득한다.
        List<MemberBean> AdminList = getAdminList(context);
        if(AdminList.size() == 0) return;

        for(int i=0; i<AdminList.size(); i++) { //for each
            MemberBean bean = AdminList.get(i);
            if( TextUtils.equals(bean.userid, memberBean.userid) ) {
                //같은 멤버ID 를 찾았다.
                AdminList.set(i, memberBean);
                break;
            }
        }
        //새롭게 update 된 리스트를 저장한다.
        String jsonStr = mGson.toJson(AdminList);
        //멤버 리스트를 저장한다.
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString("AdminList", jsonStr);
        editor.commit();
    }


    public static List<MemberBean> getAdminList(Context context) {
        String listStr = getSP(context).getString("adminList", null);
        //저장된 리스트가 없을 경우에 새로운 리스트를 리턴한다.
        if(listStr == null) {
            return new ArrayList<MemberBean>();
        }
        //Gson 으로 변환한다.
        List<MemberBean> memberList =
                mGson.fromJson(listStr, new TypeToken<List<MemberBean>>(){}.getType() );
        return memberList;
    }

    public static MemberBean getFindAdmin(Context context, String userId) {
        //1.멤버리스트를 가져온다
        List<MemberBean> adminList = getAdminList(context);
        //2.for 문 돌면서 해당 아이디를 찾는다.
        for(MemberBean bean : adminList) {
            if(TextUtils.equals(bean.userid, userId)) { //아이디가 같다.
                //3.찾았을 경우는 해당 MemberBean 을 리턴한다.
                return bean;
            }
        }
        //3-2.못찾았을 경우는??? null 리턴
        return null;
    }
    //로그인한 MemberBean 을 저장한다.
    public static void setLoginMember(Context context, MemberBean bean) {
        if(bean != null) {
            String str = mGson.toJson(bean);
            SharedPreferences.Editor editor = getSP(context).edit();
            editor.putString("loginMemberBean", str);
            editor.commit();
        }
    }

    //로그인한 MemberBean 을 취득한다.
    public static MemberBean getLoginMember(Context context) {
        String str = getSP(context).getString("loginMemberBean", null);
        if(str == null) return null;
        MemberBean memberBean = mGson.fromJson(str, MemberBean.class);
        return getFindMember(context, memberBean.userid);
    }

    //로그인한 MemberBean 을 저장한다.
    public static void setLoginAdmin(Context context, MemberBean bean) {
        if(bean != null) {
            String str = mGson.toJson(bean);
            SharedPreferences.Editor editor = getSP(context).edit();
            editor.putString("loginAdminBean", str);
            editor.commit();
        }
    }

    //로그인한 MemberBean 을 취득한다.
    public static MemberBean getLoginAdmin(Context context) {
        String str = getSP(context).getString("loginAdminBean", null);
        if(str == null) return null;
        MemberBean memberBean = mGson.fromJson(str, MemberBean.class);
        return getFindAdmin(context, memberBean.userid);
    }

}
