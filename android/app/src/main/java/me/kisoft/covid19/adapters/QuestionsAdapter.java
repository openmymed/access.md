package me.kisoft.covid19.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.kisoft.covid19.R;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.QuestionType;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private List<Question> questions;

    public QuestionsAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuestionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.ViewHolder holder, int position) {
        String question = questions.get(position).getQuestion();
        QuestionType type = questions.get(position).getType();
        holder.setData(question, type);
        //todo get binary answers...
        holder.btnTextAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo complete answering text questions
            }
        });

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAnswerNo;
        TextView tvAnswerYes;
        Button btnTextAnswer;
        private TextView tvQuestion;
        private LinearLayout llBinaryAnswer;
        private LinearLayout llTextAnswer;
        private LinearLayout llScaleAnswer;
        private EditText etAnswer;
        private SeekBar sbAnswer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tv_question);
            llBinaryAnswer = itemView.findViewById(R.id.ll_binary_answer);
            llTextAnswer = itemView.findViewById(R.id.ll_text_answer);
            llScaleAnswer = itemView.findViewById(R.id.ll_scale_answer);
            etAnswer = itemView.findViewById(R.id.et_answer);
            tvAnswerNo = itemView.findViewById(R.id.tv_answer_no);
            tvAnswerYes = itemView.findViewById(R.id.tv_answer_yes);
            sbAnswer = itemView.findViewById(R.id.sb_answer);
            btnTextAnswer = itemView.findViewById(R.id.btn_text_answer);
        }

        public void setData(String question, QuestionType type) {
            tvQuestion.setText(question);
            if (type == QuestionType.Binary) {
                llBinaryAnswer.setVisibility(View.VISIBLE);
            } else if (type == QuestionType.Text) {
                llTextAnswer.setVisibility(View.VISIBLE);
            } else if (type == QuestionType.Scale) {
                llScaleAnswer.setVisibility(View.VISIBLE);
            }
        }
    }
}
