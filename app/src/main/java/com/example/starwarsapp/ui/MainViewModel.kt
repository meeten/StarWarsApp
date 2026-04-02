package com.example.starwarsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * ViewModel для MainActivity, обеспечивающий защиту от множественных быстрых кликов.
 *
 * Проблема: навигационный вызов popBackStack() при быстрых нажатиях на кнопку "Назад"
 * в CharacterScreen мог вызываться несколько раз, что приводило к некорректному поведению.
 *
 * Решение: дебаунс 800 мс с помощью Mutex, блокирующий повторные вызовы во время выполнения
 * action и периода ожидания.
 *
 * @property clickMutex блокировка для предотвращения одновременных кликов
 * @see onClickButton обрабатывает клики с защитой от дребезга
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val clickMutex = Mutex(false)

    fun onClickButton(action: () -> Unit) {
        viewModelScope.launch {
            if (clickMutex.isLocked) return@launch
            clickMutex.withLock {
                action()
                delay(CLICK_DEBOUNCE_MILLS)
            }
        }
    }
}

private const val CLICK_DEBOUNCE_MILLS = 800L