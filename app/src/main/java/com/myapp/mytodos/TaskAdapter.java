package com.myapp.mytodos;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

//supporting class for tasks activity
//helps to create a custom layout design for a row in the tasks listing
public class TaskAdapter extends BaseAdapter {

    List<String> tasks;
    List<String> dates;
    LayoutInflater mInflater;
    ListView taskListView;

    public TaskAdapter(Context c, List<String> t, List<String> d){
        tasks = t;
        dates = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //layout design for the view is coming from task_list_detail.xml file
        View v = mInflater.inflate(R.layout.task_list_detail, null);
        TextView taskTextView = v.findViewById(R.id.taskTextView);
        TextView dateTextView = v.findViewById(R.id.dateTextView);

       //gets tasks and dates
        String taskName = tasks.get(i);
        String dateText = dates.get(i);

        //sets tasks and dates into text views displayed in every task row
        taskListView = v.findViewById(R.id.taskListView);
        taskTextView.setText(taskName);
        dateTextView.setText(dateText);

        //button for deleting tasks from the list
        Button deleteButton = v.findViewById(R.id.deleteTaskBtn);
        //sets a tag for delete button
        deleteButton.setTag(i);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(final View view) {

                //before deleting, a pop up box appears in order to confirm the delete function
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Delete task");
                alert.setMessage("Are you sure you want to delete this task?");

                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // if yes gets clicked, the chosen task will be deleted
                        //gets a index number from the tag that was set above
                        Integer index = (Integer) view.getTag();
                        // removes date and task according to the given index
                        tasks.remove(index.intValue());
                        dates.remove(index.intValue());
                        // data with new changes is written in files
                        FileHelper.writeData(tasks, view.getContext() );
                        FileHelperDates.writeData(dates, view.getContext() );
                        // shows a text which disappears in few seconds, it confirms that the task is deleted
                        Toast.makeText(view.getContext(),"Task deleted", Toast.LENGTH_SHORT).show();
                        //changed data will be notified to the view also so that the deleted row disappears right
                        //away from the list without any need to refresh view
                        notifyDataSetChanged();
                        }
                    });
                    alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // if users clicks no, closes dialog and doesn't remove any task
                            dialog.cancel();
                        }
                    });
                    alert.show();
                }
        });
             return v;
    }
}
