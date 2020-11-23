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

package com.mg.ev.ss.cardstack

import androidx.compose.animation.core.TransitionState
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.unit.dp
import com.mg.ev.ss.cardstack.ui.*

@Composable
fun ButtonContent(buttonState: MutableState<ButtonState>, state: TransitionState) {
    if (buttonState.value == ButtonState.PRESSED) { //1
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                    Modifier.width(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                        tint = state[textColor],
                        asset = Icons.Default.FavoriteBorder,
                        modifier = Modifier.size(state[pressedHeartSize])
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                    "ADD TO FAVORITES!",
                    softWrap = false,
                    modifier = Modifier.drawOpacity(state[textOpacity]), //2
                    color = state[textColor]
            )
        }
    } else {
        Icon( //3
                tint = state[textColor],
                asset = Icons.Default.Favorite,
                modifier = Modifier.size(state[idleHeartIconSize]).drawOpacity(state[iconOpacity]) //4
        )
    }

}