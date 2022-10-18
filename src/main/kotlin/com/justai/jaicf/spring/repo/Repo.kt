package com.justai.jaicf.spring.repo

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component

data class Record(val id: Int, val username: String, val scores: Int)

@Component
class Repo (val jdbcTemplate: JdbcTemplate) {
    private val rightAnswers = mapOf(
        1 to ".NET CLR",
        2 to "4",
        3 to "Он public",
        4 to "Нет, не на уровне языка. Но компилятор Kotlin использует примитивы JVM",
        5 to "Инфиксная функция, создающая пару",
        6 to "fun sum(a: Int, b: Int): Int",
        7 to "Последовательности обрабатываются лениво, итераторы жадно",
        8 to "Автоматическое преобразование из/в JSON",
        9 to "True",
        10 to "b никогда не сможет стать null",
        11 to "var i : Int = 42",
        12 to "Array<Int>",
        13 to "Класс с использованием companion object",
        14 to "Кидает исключение, если операнд равен null",
        15 to "val l: Long = \"42\".toLong()",
        16 to "Инструмент асинхронной работы в Kotlin",
        17 to "Вызывает функцию, которая вернется после вызова foo",
        18 to "Полная совместимость в обоих направлениях",
        19 to "val – final, var – нет",
        20 to "Он не компилируется, так как List – immutable",
    )

    private val mapper = RowMapper<Record> { rs, rowNum -> Record(
        rs.getInt("id"),
        rs.getString("username"),
        rs.getInt("scores"))
    }

    fun createOrResetRecord(username: String) {
        val isRecord: Boolean = jdbcTemplate
            .query("SELECT * FROM demo.results WHERE username=?",
                mapper, username).any()
        if (isRecord) {
            jdbcTemplate
                .update("UPDATE demo.results SET scores=0 WHERE username=?",
                    username)
        } else {
            jdbcTemplate
                .update("INSERT INTO demo.results(username, scores) VALUES (?, 0)",
                    username)
        }
    }

    fun writeResult(questionNumber: Int, prevQuestionAns: String, username: String) {
        if (rightAnswers[questionNumber].equals(prevQuestionAns)) {
            jdbcTemplate
                .update("UPDATE demo.results SET scores=scores+1 WHERE username=?",
                    username)
        }
    }

    fun getScores(username: String): Int? {
        return jdbcTemplate
            .query("SELECT * FROM demo.results WHERE username=?",
                mapper, username)[0].scores
    }


}