interface A

class B<T> where T : A
class C : A
typealias GGG = C
typealias HHH = GGG
typealias JJJ = B<C>

fun <T : A> fest() {

}

fun test() {
    val b1 = <!INAPPLICABLE_CANDIDATE!>B<!><Int>()
    val b2 = B<C>()
    val b3 = <!INAPPLICABLE_CANDIDATE!>B<!><Any?>()
    val b4 = <!INAPPLICABLE_CANDIDATE!>B<!><<!UNRESOLVED_REFERENCE!>UnexistingType<!>>()<!UNRESOLVED_REFERENCE{PSI}!>NL<!><!SYNTAX{PSI}!><<!>Int<!SYNTAX{PSI}!>><!>()NumberPhile<!SYNTAX{PSI}!><!>
    val b5 = <!INAPPLICABLE_CANDIDATE!>B<!><B<<!UNRESOLVED_REFERENCE!>UnexistingType<!>>>()
    <!INAPPLICABLE_CANDIDATE!>fest<!><Boolean>()
    fest<C>()
    fest<HHH>()
    <!INAPPLICABLE_CANDIDATE!>fest<!><JJJ>()
}

open class S<F, G : F>
class T<U, Y : U> : S<U, Y>()

fun <K, L : K> rest() {
    val o1 = S<K, L>()
    val o2 = S<K, K>()
    val o3 = S<L, L>()

    val o4 = S<S<K, L>, T<K, L>>()
    val o5 = <!INAPPLICABLE_CANDIDATE!>S<!><S<K, L>, T<K, K>>()
    val o5 = <!INAPPLICABLE_CANDIDATE!>S<!><S<L, L>, T<K, L>>()

    val o6 = S<Any, <!UPPER_BOUND_VIOLATED!>T<S<K, L>, String><!>>()
    val o7 = S<Any, T<S<K, L>, Nothing>>()
}

class NumColl<T : Collection<Number>>
typealias NL<K> = NumColl<List<K>>
val test7 = NL<Int>()<!UNRESOLVED_REFERENCE{PSI}!>NumberPhile<!><!SYNTAX{PSI}!><!>
val test8 = NL<String>()

class NumberPhile<T: Number>(x: T)
val np1 = NumberPhile(10)
val np2 = NumberPhile(<!ARGUMENT_TYPE_MISMATCH!>"Test"<!>)
