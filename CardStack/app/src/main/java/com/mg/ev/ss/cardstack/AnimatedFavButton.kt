package com.mg.ev.ss.cardstack

/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.AnimationConstants.Infinite
import androidx.compose.animation.transition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.mg.ev.ss.cardstack.FavButton
import com.mg.ev.ss.cardstack.ui.*

@SuppressLint("Range")
@Preview
@Composable
fun AnimatedFavButton() {
    val buttonState = remember { mutableStateOf(ButtonState.IDLE) }

    val transitionDefinition = transitionDefinition<ButtonState> {
        state(ButtonState.IDLE) {
            this[width] = 300.dp
            this[roundedCorners] = 6
            this[textColor] = purple500
            this[backgroundColor] = Color.White
            this[textOpacity] = 1f
            this[iconOpacity] = 0f
            this[pressedHeartSize] = 48.dp
            this[idleHeartIconSize] = 24.dp
        }
        state(ButtonState.PRESSED) {
            this[width] = 50.dp
            this[roundedCorners] = 50
            this[textColor] = Color.White
            this[backgroundColor] = purple500
            this[textOpacity] = 0f
            this[iconOpacity] = 1f
            this[pressedHeartSize] = 48.dp
            this[idleHeartIconSize] = 24.dp
        }
        transition(fromState = ButtonState.IDLE, toState = ButtonState.PRESSED) {
            width using tween(durationMillis = 1500)
            roundedCorners using tween(
                    durationMillis = 3000,
                    easing = FastOutLinearInEasing
            )
            backgroundColor using tween(durationMillis = 3000)
            textColor using tween(durationMillis = 500)
            textOpacity using tween(durationMillis = 1500)
            iconOpacity using tween(durationMillis = 1500)
            pressedHeartSize using keyframes {
                48.dp at 1700
                12.dp at 1900
                durationMillis = 5000
            }
            idleHeartIconSize using repeatable( // 1
                    animation = keyframes { //2
                        durationMillis = 2000
                        24.dp at 1400
                        12.dp at 1500
                        24.dp at 1600
                        12.dp at 1700
                    },
                    iterations = Infinite
            )
        }
        transition(fromState = ButtonState.PRESSED, toState = ButtonState.IDLE) {
            width using tween(durationMillis = 1500)
            roundedCorners using tween(
                    durationMillis = 3000,
                    easing = FastOutLinearInEasing
            )
            backgroundColor using tween(durationMillis = 3000)
            textColor using tween(durationMillis = 500)
            textOpacity using tween(durationMillis = 3000)
            iconOpacity using tween(durationMillis = 3000)
        }
    }

    val toState = if (buttonState.value == ButtonState.IDLE) {
        ButtonState.PRESSED
    } else {
        ButtonState.IDLE
    }

    val state = transition(
            definition = transitionDefinition,
            initState = buttonState.value,
            toState = toState
    )

    FavButton(buttonState = buttonState, state = state)
}

enum class ButtonState {
    IDLE, PRESSED
}