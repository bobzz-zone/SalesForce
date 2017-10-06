package mtisfa.com.sfa.control;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import mtisfa.com.sfa.Utility.MyCookieJar;
import mtisfa.com.sfa.Utility.Utility;
import mtisfa.com.sfa.model.API;
import mtisfa.com.sfa.model.Assignment;

/**
 * Created by Windows 8.1 on 25/09/2017.
 */

public class AssignmentControl {
    Context context;
    public Realm realm;
    public AssignmentControl(Context app){
        context=app;
        realm=Realm.getDefaultInstance();
    }
    public void makeEmpty(){
        realm.beginTransaction();
        final RealmResults<Assignment> assignment_list = realm.where(Assignment.class).findAll();
        assignment_list.deleteAllFromRealm();
        realm.commitTransaction();
    }
    public void addFromList(List<Assignment> list){
        realm.beginTransaction();
        realm.copyToRealm(list);
        realm.commitTransaction();
    }
    public List<Assignment> getAll(){
        List<Assignment> listData = realm.where(Assignment.class).findAll();
        return listData;
    }
    public void close(){
        realm.close();
    }
}
