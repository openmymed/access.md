package me.kisoft.covid19.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import me.kisoft.covid19.fragments.MeasurementFragment;
import me.kisoft.covid19.models.Answer;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.QuestionType;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceDelegate;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private List<Question> questions;
    private Context context;
    private PatientService service;

    public QuestionsAdapter(List<Question> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_layout, parent, false);
        service = new PatientServiceDelegate();
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull final QuestionsAdapter.ViewHolder holder, int position) {
        final Question question = questions.get(position);
        final String questionText = questions.get(position).getQuestion();
        QuestionType type = questions.get(position).getType();
        holder.setData(questionText, type);

        //This event is used to handle the answer for text questions only..
        holder.btnTextAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer answer = new Answer(holder.etAnswer.getText().toString());
                holder.createAnswer(answer, question);
            }
        });
        holder.tvAnswerNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onClick(view, question);
            }
        });
        holder.tvAnswerYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.onClick(view, question);
            }
        });

        //This event is used to answer the vital question only..
        holder.btnVitalAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo check if this works
                Intent intent = new Intent(context, MeasurementFragment.class);
                context.startActivity(intent);
                //sending empty answer object.
                holder.createAnswer(new Answer(), question);
            }
        });

        //This event is used to answer the scale questions only..
        holder.btnScaleAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer answer = new Answer(String.valueOf(holder.sbAnswer.getProgress()));
                holder.createAnswer(answer, question);
            }
        });

        //This event used to change the value of the text of seekbar.
        holder.sbAnswer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                holder.tvScaleValue.setText(String.valueOf(i) + "/100");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
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
        Button btnScaleAnswer;
        Button btnVitalAnswer;
        TextView tvScaleValue;
        EditText etAnswer;
        SeekBar sbAnswer;
        private TextView tvQuestion;
        private LinearLayout llBinaryAnswer;
        private LinearLayout llTextAnswer;
        private LinearLayout llScaleAnswer;
        private LinearLayout llVitalAnswer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tv_question);
            llBinaryAnswer = itemView.findViewById(R.id.ll_binary_answer);
            llTextAnswer = itemView.findViewById(R.id.ll_text_answer);
            llScaleAnswer = itemView.findViewById(R.id.ll_scale_answer);
            llVitalAnswer = itemView.findViewById(R.id.ll_vital_answer);
            etAnswer = itemView.findViewById(R.id.et_answer);
            tvAnswerNo = itemView.findViewById(R.id.tv_answer_no);
            tvAnswerYes = itemView.findViewById(R.id.tv_answer_yes);
            sbAnswer = itemView.findViewById(R.id.sb_answer);
            btnTextAnswer = itemView.findViewById(R.id.btn_text_answer);
            btnScaleAnswer = itemView.findViewById(R.id.btn_scale_answer);
            btnVitalAnswer = itemView.findViewById(R.id.btn_vital_answer);
            tvScaleValue = itemView.findViewById(R.id.tv_scale_value);
        }

        //this method is used to create the question.
        public void setData(String question, QuestionType type) {
            tvQuestion.setText(question);
            if (type == QuestionType.Binary) {
                llBinaryAnswer.setVisibility(View.VISIBLE);
            } else if (type == QuestionType.Text) {
                llTextAnswer.setVisibility(View.VISIBLE);
            } else if (type == QuestionType.Scale) {
                llScaleAnswer.setVisibility(View.VISIBLE);
            } else if (type == QuestionType.Vitals) {
                llVitalAnswer.setVisibility(View.VISIBLE);
            }
        }

        //This method is used to answer the binary questions only..
        public void onClick(View view, Question question) {
            Answer answer = new Answer();
            if (view == tvAnswerNo) {
                answer.setAnswer("false");
            } else if (view == tvAnswerYes) {
                answer.setAnswer("true");
            }
            createAnswer(answer, question);

        }

        public void createAnswer(final Answer answer, final Question question) {
            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected Boolean doInBackground(Void... voids) {
                    return service.answerQuestion(answer, question.getId());
                }

                @Override
                protected void onPostExecute(Boolean result) {
                    super.onPostExecute(result);
                    if (result) {
                        int i = questions.indexOf(question);
                        questions.remove(i);
                        notifyItemRemoved(i);
                        notifyItemRangeChanged(i, questions.size());
                        notifyDataSetChanged();
                    }
                }
            }.execute();
        }
    }
}
