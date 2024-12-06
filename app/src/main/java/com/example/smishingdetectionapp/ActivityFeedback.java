package com.example.smishingdetectionapp;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View; // Add this import for the View class
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ActivityFeedback extends AppCompatActivity {
    private ImageView emojiAngry, emojiSad, emojiNeutral, emojiHappy, emojiExcited;
    private EditText inputFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Initialize views
        ImageButton backButton = findViewById(R.id.report_back);
        emojiAngry = findViewById(R.id.emojiAngry);
        emojiSad = findViewById(R.id.emojiSad);
        emojiNeutral = findViewById(R.id.emojiNeutral);
        emojiHappy = findViewById(R.id.emojiHappy);
        emojiExcited = findViewById(R.id.emojiExcited);
        inputFeedback = findViewById(R.id.inputFeedback);
        View btnSubmit = findViewById(R.id.btnSubmit);

        // Back button functionality
        backButton.setOnClickListener(v -> finish());

        // Set hover listeners for emojis
        setEmojiHoverEffect(emojiAngry);
        setEmojiHoverEffect(emojiSad);
        setEmojiHoverEffect(emojiNeutral);
        setEmojiHoverEffect(emojiHappy);
        setEmojiHoverEffect(emojiExcited);

        // Submit button functionality
        btnSubmit.setOnClickListener(v -> {
            String feedbackText = inputFeedback.getText().toString().trim();
            if (!feedbackText.isEmpty()) {
                // Handle feedback submission (e.g., send to server or save locally)
                Toast.makeText(ActivityFeedback.this, "Feedback submitted! Thank you.", Toast.LENGTH_SHORT).show();
                inputFeedback.setText(""); // Clear the input field
            } else {
                Toast.makeText(ActivityFeedback.this, "Please enter your feedback.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setEmojiHoverEffect(ImageView emoji) {
        emoji.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Change the emoji color to indicate hover
                    emoji.setColorFilter(ContextCompat.getColor(this, R.color.emoji_hover));
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    // Revert to the default color
                    emoji.setColorFilter(ContextCompat.getColor(this, R.color.emoji_normal));
                    break;
            }
            return true;
        });

        emoji.setOnClickListener(v -> {
            // Change to selected color when clicked
            emoji.setColorFilter(ContextCompat.getColor(this, R.color.emoji_selected));
            Toast.makeText(this, "Emoji selected!", Toast.LENGTH_SHORT).show();
        });
    }
}