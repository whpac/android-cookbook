package pl.put.cookbook

class Timer(duration: Long) {
    var isRunning: Boolean = true
        private set

    // Used only when the timer is running. When stopped, can be wrong
    private var endTime: Long

    // Used only when the timer is stopped. When running, can be wrong
    private var remainingSeconds: Long = 0

    init {
        endTime = now() + duration
        remainingSeconds = duration
    }

    private fun now(): Long {
        return System.currentTimeMillis() / 1000
    }

    fun getRemainingTime(): Long {
        return if(isRunning) {
            endTime - now()
        }else{
            remainingSeconds
        }
    }

    fun start() {
        endTime = now() + getRemainingTime()
        isRunning = true
    }

    fun stop() {
        remainingSeconds = getRemainingTime()
        isRunning = false
    }
}