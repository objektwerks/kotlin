package stdlib

import org.junit.Test

sealed class Expr
class Const(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Int =
    when (expr) {
        is Const -> expr.value
        is Sum -> eval(expr.right) + eval(expr.left)
        NotANumber -> Double.NaN.toInt()
    }

class SealedClassTest {
    @Test fun sealedClass() {
        assert( eval( Sum( Const(1), Const(2) ) ) == 3)
    }
}