package com.example.quizappinkotlin

object setData {

    const val name: String = "UserName"
    const val score: String = "0"
    const val avImg: Int = 0

    fun getQuestion(): ArrayList<QuestionData> {

        var que: ArrayList<QuestionData> = arrayListOf()


        var q1 = QuestionData(
            "What paradigm(s) does the Kotlin programming language follow?",
            1,
            "Only Object-Oriented",
            "Procedural",
            "Only Functional",
            "Both Object-Oriented and Functional",
            4
        )

        var q2 = QuestionData(
            "How do you define a variable in Kotlin that cannot be reassigned?",
            2,
            "var",
            "val",
            "const",
            "final",
            2
        )

        var q3 = QuestionData(
            "Which platform does Kotlin primarily target?",
            3,
            "Python Bytecode",
            "JavaScript",
            "JVM (Java Virtual Machine) Bytecode",
            "PHP",
            3
        )

        var q4 = QuestionData(
            "How do you define a function in Kotlin?",
            4,
            "fun myFunction() {}",
            "def myFunction() {}",
            "function myFunction() {}",
            "fun = myFunction() {}",
            1
        )

        var q5 = QuestionData(
            "How do you declare a nullable variable in Kotlin?",
            5,
            "var name: String?",
            "var name: String",
            "var name: String = null",
            "String name = null",
            1
        )

        var q6 = QuestionData(
            "What does ?. operator do in Kotlin?",
            6,
            "Null-safe type casting",
            "Null-safe function calling",
            "Null-safe member access",
            "None of the above",
            3
        )

        var q7 = QuestionData(
            "What is the default visibility modifier for functions in Kotlin if no modifier is specified?",
            7,
            "public",
            "private",
            "internal",
            "protected",
            1
        )

        var q8 = QuestionData(
            "Which keyword is used to create a singleton in Kotlin?",
            8,
            "static",
            "singleton",
            "single",
            "object",

            4
        )

        var q9 = QuestionData(
            "Which feature in Kotlin helps to prevent NullPointerExceptions?",
            9,
            "Safe Call Operator (?.)",
            "Non-null Assertion Operator (!!)",
            "Elvis Operator (?:)",
            "Safe Cast Operator (as?)",
            1
        )

        var q10 = QuestionData(
            "What is the role of the init block in Kotlin?",
            10,
            "To initialize the superclass",
            "To initialize an object after the constructor has been called",
            "To initialize static variables",
            "None of the above",
            2
        )






        que.add(q1)
        que.add(q2)
        que.add(q3)
        que.add(q4)
        que.add(q5)
        que.add(q6)
        que.add(q7)
        que.add(q8)
        que.add(q9)
        que.add(q10)

        return que
    }

}