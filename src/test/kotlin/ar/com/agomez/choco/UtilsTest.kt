/*
 * Copyright 2018-present by the authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at following link.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ar.com.agomez.choco

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author Alejandro Gomez
 */
class UtilsTest {

    @Test
    fun testAsTypedArrayFromSet() {
        Assertions.assertThat(setOf("a", "b", "c").asTypedArray()).containsExactlyInAnyOrder("a", "b", "c")
    }

    @Test
    fun testAsTypedArrayFromList() {
        Assertions.assertThat(listOf("a", "b", "c").asTypedArray()).containsExactly("a", "b", "c")
    }

    @Test
    fun testAsTypedArrayFromRange() {
        Assertions.assertThat((1L..3L).asTypedArray()).containsExactly(1L, 2L, 3L)
    }

    @Test
    fun testAsIntArrayFromSet() {
        Assertions.assertThat(setOf(1, 2, 3).asIntArray()).containsExactlyInAnyOrder(1, 2, 3)
    }

    @Test
    fun testAsIntArrayFromList() {
        Assertions.assertThat(listOf(1, 2, 3).asIntArray()).containsExactly(1, 2, 3)
    }

    @Test
    fun testAsIntArrayFromRange() {
        Assertions.assertThat((1..3).asIntArray()).containsExactly(1, 2, 3)
    }
}
