package tripletail.stdlib

import org.junit.Test

sealed class Expr
class Const(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()
object NotANumber : Expr()

fun eval(e: Expr): Int =
    when (e) {
        is Const -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        NotANumber -> Double.NaN.toInt()
    }

class SealedClassTest {
    @Test fun sealedClass() {
        assert( eval( Sum( Const(1), Const(2) ) ) == 3)
    }
}