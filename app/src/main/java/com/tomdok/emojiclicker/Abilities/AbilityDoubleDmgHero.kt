package com.tomdok.emojiclicker.Abilities

import com.tomdok.emojiclicker.classes.Emote
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class AbilityDoubleDmgHero(_duration: Long, _picture: Int): Ability {

    var duration = _duration
    override val PICTURE = _picture
    override val DESCRIPTION: String?
        get() = "Viking starts to be in rage, this will cause him to do double damage."

    override fun doEffects(player: Player, hero: Hero, emote: Emote, refreshHPBar: () -> Unit) {

        hero.baseDPS *= 2
        runBlocking {

            launch {

                delay(duration)
            }
        }

        hero.baseDPS /= 2

    }

}