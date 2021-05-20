package com.compscieddy.customtextselection

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.compscieddy.customtextselection.databinding.FloatingActivityBinding

const val MAX_ANIMATION_DURATION = 300L
const val INITIAL_TRANSLATION_Y_FOR_COPIED_TEXT = 100f

class FloatingActivity : Activity() {

    private lateinit var binding: FloatingActivityBinding
    private var copiedText: CharSequence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FloatingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        copiedText = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)

        initCopiedText()

        runBlackFadeAnimation(isEnter = true)

        binding.copiedText.translationY = INITIAL_TRANSLATION_Y_FOR_COPIED_TEXT
        runCopiedTextAnimation(isEnter = true)
    }

    override fun onBackPressed() {
        runCopiedTextAnimation(isEnter = false)
        runBlackFadeAnimation(isEnter = false) {
            super.onBackPressed()
        }
    }

    private fun initCopiedText() {
        binding.copiedText.setText(copiedText)
    }

    private fun runBlackFadeAnimation(isEnter: Boolean, onEnd: () -> Unit = {}) {
        val alpha = if (isEnter) 0.35f else 0f
        binding.blackUnderlay.animate()
            .setDuration(MAX_ANIMATION_DURATION)
            .alpha(alpha)
            .withEndAction(onEnd)
    }

    private fun runCopiedTextAnimation(isEnter: Boolean) {
        val alpha = if (isEnter) 1f else 0f
        val translationY = if (isEnter) 0f else INITIAL_TRANSLATION_Y_FOR_COPIED_TEXT
        binding.copiedText.animate()
            .setDuration((MAX_ANIMATION_DURATION * 0.8f).toLong())
            .alpha(alpha)
            .translationY(translationY)
    }

}