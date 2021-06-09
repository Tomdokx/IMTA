package com.tomdok.emojiclicker.Abilities

import com.tomdok.emojiclicker.classes.Emote
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AbilityPowerUpPlayer(_scale: Double, _duration: Long): Ability {

    var scale = _scale
    var duration = _duration

    override fun doEffects(player: Player, hero: Hero, emote: Emote, refreshHPBar: () -> Unit) {

        player.dps *= scale
        runBlocking {

            launch {

                delay(duration)
            }
        }

        player.dps /= scale

    }

}