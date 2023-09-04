package com.emotiontracker.presentation.datasource

import com.emotiontracker.R


sealed class Emotion {
    abstract val name: Int
    abstract val description: Int
    abstract val color: Int

    abstract fun getIntensity(): Triple<Emotion, Emotion, Emotion>

    sealed class Angry : Emotion() {

        override val color: Int = R.color.angry
            override fun getIntensity(): Triple<Emotion, Emotion, Emotion> {
            return Triple(Frustration(), Anger(), Rage())
        }
        class Frustration: Angry() {
            override val name: Int = R.string.frustration
            override val description: Int = R.string.angry_light_description
        }
        class Anger: Angry() {
            override val name: Int = R.string.angry
            override val description: Int = R.string.angry_description
        }
        class Rage: Angry() {
            override val name: Int = R.string.rage
            override val description: Int = R.string.angry_hard_description
        }
    }

    sealed class Fearing : Emotion() {

        override val color: Int = R.color.fear
        override fun getIntensity(): Triple<Emotion, Emotion, Emotion> {
            return Triple(Anxiety(), Fear(), Horror())
        }

        class Anxiety: Fearing() {
            override val name: Int = R.string.anxiety
            override val description: Int = R.string.fear_light_description
        }
        class Fear: Fearing() {
            override val name: Int = R.string.fear
            override val description: Int = R.string.fear_description
        }
        class Horror: Fearing() {
            override val name: Int = R.string.horror
            override val description: Int = R.string.fear_hard_description
        }
    }

    sealed class Surprising : Emotion() {

        override val color: Int = R.color.surprise

        override fun getIntensity(): Triple<Emotion, Emotion, Emotion> {
            return Triple(Distraction(), Surprise(), Astonishment())
        }
        class Distraction: Surprising() {
            override val name: Int = R.string.distraction
            override val description: Int = R.string.surprise_light_description
        }
        class Surprise: Surprising() {
            override val name: Int = R.string.surprise
            override val description: Int = R.string.surprise_description
        }
        class Astonishment: Surprising() {
            override val name: Int = R.string.astonishment
            override val description: Int = R.string.surprise_hard_description
        }
    }

    sealed class Sadness : Emotion() {

        override val color: Int = R.color.sad

        override fun getIntensity(): Triple<Emotion, Emotion, Emotion> {
            return Triple(Blue(), Sad(), Grief())
        }
        class Blue : Sadness() {
            override val name: Int = R.string.blue
            override val description: Int = R.string.sad_light_description
        }
        class Sad : Sadness() {
            override val name: Int = R.string.sad
            override val description: Int = R.string.sad_description
        }
        class Grief : Sadness() {
            override val name: Int = R.string.grief
            override val description: Int = R.string.sad_hard_description
        }
    }

    sealed class Aversion : Emotion() {

        override val color: Int = R.color.dislike

        override fun getIntensity(): Triple<Emotion, Emotion, Emotion> {
            return Triple(Disapproval(), Dislike(), Disgust())
        }
        class Disapproval : Aversion() {
            override val name: Int = R.string.disapproval
            override val description: Int = R.string.dislike_light_description
        }
        class Dislike: Aversion() {
            override val name: Int = R.string.dislike
            override val description: Int = R.string.dislike_description
        }
        class Disgust: Aversion() {
            override val name: Int = R.string.disgust
            override val description: Int = R.string.dislike_hard_description
        }
    }

    sealed class Interesting : Emotion() {

        override val color: Int = R.color.interest

        override fun getIntensity(): Triple<Emotion, Emotion, Emotion> {
            return Triple(Interest(), Anticipation(), Vigilance())
        }
        class Interest : Interesting() {
            override val name: Int = R.string.interest
            override val description: Int = R.string.interest_light_description
        }
        class Anticipation : Interesting() {
            override val name: Int = R.string.anticipation
            override val description: Int = R.string.interest_description
        }
        class Vigilance : Interesting() {
            override val name: Int = R.string.vigilance
            override val description: Int = R.string.interest_hard_description
        }
    }

    sealed class Glad : Emotion() {

        override val color: Int = R.color.joy

        override fun getIntensity(): Triple<Emotion, Emotion, Emotion> {
            return Triple(Serenity(), Joy(), Delight())
        }
        class Serenity: Glad() {
            override val name: Int = R.string.serenity
            override val description: Int = R.string.joy_light_description
        }
        class Joy: Glad() {
            override val name: Int = R.string.joy
            override val description: Int = R.string.joy_description
        }
        class Delight: Glad() {
            override val name: Int = R.string.delight
            override val description: Int = R.string.joy_hard_description
        }
    }

    sealed class Trusting : Emotion() {
        override val color: Int = R.color.trust

        override fun getIntensity(): Triple<Emotion, Emotion, Emotion> {
            return Triple(Approval(), Trust(), Admiration())
        }
        class Approval: Trusting() {
            override val name: Int = R.string.approval
            override val description: Int = R.string.trust_light_description
        }
        class Trust: Trusting() {
            override val name: Int = R.string.trust
            override val description: Int = R.string.trust_description
        }
        class Admiration: Trusting() {
            override val name: Int = R.string.admiration
            override val description: Int = R.string.trust_hard_description
        }
    }

    companion object {
        private fun getAllEmotions(): List<Emotion> {
            return listOf<Emotion>(
                Angry.Anger(),
                Angry.Frustration(),
                Angry.Rage(),
                Fearing.Fear(),
                Fearing.Anxiety(),
                Fearing.Horror(),
                Surprising.Surprise(),
                Surprising.Distraction(),
                Surprising.Astonishment(),
                Sadness.Sad(),
                Sadness.Blue(),
                Sadness.Grief(),
                Aversion.Dislike(),
                Aversion.Disapproval(),
                Aversion.Disgust(),
                Interesting.Anticipation(),
                Interesting.Interest(),
                Interesting.Vigilance(),
                Glad.Joy(),
                Glad.Serenity(),
                Glad.Delight(),
                Trusting.Trust(),
                Trusting.Approval(),
                Trusting.Admiration()
            )
        }

        fun getFromSimpleName(name: String): Emotion? {
            return getAllEmotions().find { it::class.simpleName == name }
        }
    }
}

