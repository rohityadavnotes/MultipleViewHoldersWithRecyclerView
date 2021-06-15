package multiple.view.holders.with.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MultipleViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int TYPE_CALL  = 1;
    private static final int TYPE_SMS   = 2;

    private Context context;
    private List<Object> callSmsObjectList =new ArrayList();

    public MultipleViewTypeAdapter(Context context)
    {
        this.context=context;
    }

    public void setCallSMSFeed(List<Object> callSmsObjectList){
        this.callSmsObjectList=callSmsObjectList;
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount(){return callSmsObjectList.size();}

    @Override
    public int getItemViewType(int position)
    {
        if (callSmsObjectList.get(position) instanceof Call)
        {
            return TYPE_CALL;
        }
        else if (callSmsObjectList.get(position) instanceof SMS)
        {
            return TYPE_SMS;
        }
        else
        {
            return -1;
        }
    }

    /**
     * This method creates different RecyclerView.ViewHolder objects based on the item view type.\
     *
     * @param viewGroup ViewGroup container for the item
     * @param viewType type of view to be inflated
     * @return viewHolder to be inflated
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType)
        {
            case TYPE_CALL:
                View callsView = inflater.inflate(R.layout.calls_feed_layout, viewGroup, false);
                viewHolder=new CallViewHolder(callsView);
                break;
            case TYPE_SMS:
                View smsView = inflater.inflate(R.layout.sms_feed_layout, viewGroup, false);
                viewHolder=new SMSViewHolder(smsView);
                break;
            default:
                viewHolder=null;
                break;
        }
        return viewHolder;
    }

    /**
     * This method internally calls onBindViewHolder(ViewHolder, int) to update the
     * RecyclerView.ViewHolder contents with the item at the given position
     * and also sets up some private fields to be used by RecyclerView.
     *
     * @param viewHolder The type of RecyclerView.ViewHolder to populate
     * @param position Item position in the viewgroup.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        switch (viewHolder.getItemViewType())
        {
            case TYPE_CALL:
                Call call=(Call)callSmsObjectList.get(position);
                ((CallViewHolder)viewHolder).showCallDetails(call);

                ((CallViewHolder) viewHolder).callerNameTextView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Caller Name TextView : "+position, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case TYPE_SMS:
                SMS sms=(SMS)callSmsObjectList.get(position);
                ((SMSViewHolder)viewHolder).showSmsDetails(sms);
                break;
        }
    }

    public class CallViewHolder extends RecyclerView.ViewHolder
    {
        private TextView callerNameTextView;
        private TextView callTimeTextView;

        public CallViewHolder(View itemView)
        {
            super(itemView);
            callerNameTextView=(TextView)itemView.findViewById(R.id.callerName);
            callTimeTextView=(TextView)itemView.findViewById(R.id.callTime);
        }

        public void showCallDetails(Call call)
        {
            String callerName   =call.getCallerName();
            String callTime     =call.getCallTime();
            callerNameTextView.setText(callerName);
            callTimeTextView.setText(callTime);
        }
    }

    public class SMSViewHolder extends RecyclerView.ViewHolder
    {
        private TextView senderNameTextView;
        private TextView smsContentTextView;
        private TextView smsTimeTextView;

        SMSViewHolder(@NonNull View itemView)
        {
            super(itemView);
            senderNameTextView =(TextView)itemView.findViewById(R.id.senderName);
            smsContentTextView =(TextView)itemView.findViewById(R.id.smsContent);
            smsTimeTextView =(TextView)itemView.findViewById(R.id.smsTime);
        }

        public void showSmsDetails(SMS sms)
        {
            String senderName   =sms.getSenderName();
            String smsContent   =sms.getSmsContent();
            String smsTime     =sms.getSmsTime();
            senderNameTextView.setText(senderName);
            smsContentTextView.setText(smsContent);
            smsTimeTextView.setText(smsTime);
        }
    }
}