package function;

public class FunctionFactory{

    public IFunction create(String name){
        for (FuncEnum f : FuncEnum.values()) {
            if (f.getStr().equals(name)) {
                return f.getFunc();
            }
        }
        throw new IllegalArgumentException();
    }
}
