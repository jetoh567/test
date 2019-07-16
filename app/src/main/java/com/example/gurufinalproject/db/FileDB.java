package com.example.gurufinalproject.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.gurufinalproject.bean.MemberBean;
import com.example.gurufinalproject.bean.NoteBean;
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
    //기존 멤버 교체
    public static void setMember(Context context, MemberBean memberBean) {
        //전체 멤버 리스트를 취득한다.
        List<MemberBean> memberList = getMemberList(context);
        if(memberList.size() == 0) return;

        for(int i=0; i<memberList.size(); i++) { //for each
            MemberBean bean = memberList.get(i);
            if( TextUtils.equals(bean.userid, memberBean.userid) ) {
                //같은 멤버ID 를 찾았다.
                memberList.set(i, memberBean);
                break;
            }
        }
        //새롭게 update 된 리스트를 저장한다.
        String jsonStr = mGson.toJson(memberList);
        //멤버 리스트를 저장한다.
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString("memberList", jsonStr);
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

    //로그인한 MemberBean 을 취득한다.
    public static MemberBean getLoginMember(Context context) {
        String str = getSP(context).getString("loginMemberBean", null);
        if(str == null) return null;
        MemberBean memberBean = mGson.fromJson(str, MemberBean.class);
        return getFindMember(context, memberBean.userid);
    }

    //글을 추가하는 메서드
    public static void addNote(Context context, String memId, NoteBean noteBean) {
        MemberBean findMember = getFindMember(context, memId);
        if(findMember == null) return;

        List<NoteBean> noteList = findMember.noteList;
        if(noteList == null) {
            noteList = new ArrayList<>();
        }
        //고유 글 ID 를 생성해준다.
        noteBean.noteId = System.currentTimeMillis();
        noteList.add(noteBean);
        findMember.noteList = noteList;
        //저장
        setMember(context, findMember);
    }

    //기존 글 교체
    public static void setNote(Context context, NoteBean noteBean) {
        MemberBean memberBean = getLoginMember(context);
        if(memberBean == null || memberBean.noteList== null) {
            return;
        }
        List<NoteBean> noteList = memberBean.noteList;
        for(int i=0; i<noteList.size(); i++) {
            NoteBean mBean = noteList.get(i);
            if(mBean.noteId == noteBean.noteId) {
                //찾았다.
                noteList.set(i, noteBean); //교체
                break;
            }
        }
        //업데이트된 글 리스트를 저장
        memberBean.noteList = noteList;
        setMember(context, memberBean);
    }
    //글 삭제
    public static void delNote(Context context, long noteId) {
        MemberBean memberBean = getLoginMember(context);
        List<NoteBean> memoList = memberBean.noteList;
        if(memoList == null) return;

        for(int i=0; i<memoList.size(); i++) {
            NoteBean mBean = memoList.get(i);
            if(mBean.noteId == noteId) {
                //찾았다.
                memoList.remove(i);
                break;
            }
        }
        //저장
        memberBean.noteList = memoList;
        setMember(context, memberBean);
    }

    public static NoteBean getMemo(Context context, long memoId) {
        MemberBean memberBean = getLoginMember(context);
        List<NoteBean> memoList = memberBean.noteList;
        if(memoList == null) return null;

        for(NoteBean bean : memoList) {
            if(bean.noteId == memoId) {
                //찾았다.
                return bean;
            }
        }
        return null;
    }

    //글 리스트 취득
    public static List<NoteBean> getMemoList(Context context) {
        MemberBean memberBean = getLoginMember(context);
        if(memberBean == null) return null;

        if(memberBean.noteList == null) {
            return new ArrayList<>();
        }
        else {
            return memberBean.noteList;
        }
    }


}