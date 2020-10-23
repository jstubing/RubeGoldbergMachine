package com.willowtree.rubegoldbergmachine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.willowtree.rubegoldbergmachine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        // Set this to your turn number (turn 1 goes first, turn 2 goes second, etc...)
        private const val TURN: Long = 1
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeTurn()
    }

    private fun doTheWork() {
        // Do something fun! It could be as simple as playing an animated GIF and/or sound.
        // Recommend keeping it short (< 1 min) to keep the machine moving.

        // Call this after your turn is complete. It will signal the next turn to begin.
        startNextTurn()
    }

    private fun observeTurn() {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val observedTurn = snapshot.value as Long

                if (observedTurn == TURN) {
                    doTheWork()
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        }

        val database = FirebaseDatabase.getInstance()
        val turnRef = database.getReference("turn")
        turnRef.addValueEventListener(eventListener)
    }

    private fun startNextTurn() {
        val database = FirebaseDatabase.getInstance()
        val turnRef = database.getReference("turn")
        turnRef.setValue(TURN + 1)
    }
}