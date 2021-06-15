package multiple.view.holders.with.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Object> callSmsObjectList;
    private MultipleViewTypeAdapter callSMSAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        init();
    }

    private void init()
    {
        callSmsObjectList =new ArrayList<>();
        callSMSAdapter =new MultipleViewTypeAdapter(RecyclerViewActivity.this);
        recyclerView.setAdapter(callSMSAdapter);

        callSmsObjectList.add(new Call("John","9:30 AM"));
        callSmsObjectList.add(new Call("Rob","9:40 AM"));
        callSmsObjectList.add(new SMS("Sandy","Hey, what's up?","9:42 AM"));
        callSmsObjectList.add(new Call("Peter","9:45 AM"));
        callSmsObjectList.add(new SMS("John","Are you writing blog?","9:48 AM"));
        callSmsObjectList.add(new Call("Jack","9:50 AM"));
        callSmsObjectList.add(new Call("Bob","9:55 AM"));
        callSmsObjectList.add(new SMS("Kora","Thanks dude","9:57 AM"));
        callSmsObjectList.add(new Call("Sandy","10:00 AM"));
        callSmsObjectList.add(new Call("Kate","10:05 AM"));
        callSmsObjectList.add(new SMS("Nick","Let's hang up","10:10 AM"));
        callSmsObjectList.add(new Call("Roger","10:15 AM"));
        callSmsObjectList.add(new Call("Sid","10:20 AM"));
        callSmsObjectList.add(new Call("Kora","10:25 AM"));
        callSmsObjectList.add(new Call("Nick","10:30 AM"));
        callSmsObjectList.add(new SMS("Rose","Bring me some chocolates","1035:10 AM"));
        callSmsObjectList.add(new Call("Mia","10:40 AM"));
        callSmsObjectList.add(new Call("Scott","10:45 AM"));

        callSMSAdapter.setCallSMSFeed(callSmsObjectList);
        callSMSAdapter.notifyDataSetChanged();
    }
}

