package com.timkontrakan.myflexibelfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.timkontrakan.myflexibelfragment.databinding.FragmentOptionDialogBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionDialogFragment extends DialogFragment implements View.OnClickListener {

    FragmentOptionDialogBinding binding;
    OnOptionDialogListener onOptionDialogListener;

    public OptionDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOptionDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnChoose.setOnClickListener(this);
        binding.btnClose.setOnClickListener(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Fragment fragment = getParentFragment();
        if (fragment instanceof DetailCategoryFragment){
            DetailCategoryFragment detailCategoryFragment = (DetailCategoryFragment) fragment;
            this.onOptionDialogListener = detailCategoryFragment.onOptionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.onOptionDialogListener = null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_close:
                getDialog().cancel();
                break;

            case R.id.btn_choose:

                Toast.makeText(getActivity(), "Pastikan Anda Sudah Memilih Pelatih Terbaik Munyuk United", Toast.LENGTH_SHORT).show();

                int checkedRadioButtonId = binding.rgOptions.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    String coach = null;
                    switch (checkedRadioButtonId) {
                        case R.id.rb_saf:
                            coach = binding.rbSaf.getText().toString().trim();
                            break;
                        case R.id.rb_mou:
                            coach = binding.rbMou.getText().toString().trim();
                            break;
                        case R.id.rb_lvg:
                            coach = binding.rbLvg.getText().toString().trim();
                            break;
                        case R.id.rb_moyes:
                            coach = binding.rbMoyes.getText().toString().trim();
                            break;
                    }

                    if (onOptionDialogListener != null) {
                        onOptionDialogListener.onOptionChosen(coach);
                        getDialog().dismiss();
                    }
                }


                break;
        }
    }

    public interface OnOptionDialogListener {
        void onOptionChosen(String text);
    }


}
