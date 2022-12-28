package function;

public enum FuncEnum {
    FUNC1("e^sin(x*3) - cos (y^2)", new Function1()),
    FUNC2("x^3 - y^2", new Function2()),
    FUNC3("x^(1/3) * sin(y)", new Function3()),
    FUNC4("ln(x^2 + 1) / (y^2 + 2)", new Function4()),
    FUNC5("(1 / (x^3 + 2)) + ln (y)", new Function5()),
    FUNC6("abs(y^4 - y^3 + x^2 - x)", new Function6()),
    FUNC7("x^4+y^4*(x^2+y^2)", new Function7()),
    FUNC8("e^((sin(x) + cos(x)) / (x^2 + 1))", new Function8());

    private final String str;
    private final IFunction func;

    FuncEnum(String str, IFunction func) {
        this.str = str;
        this.func = func;
    }

    public IFunction getFunc() {
        return func;
    }

    public String getStr() {
        return str;
    }
}
