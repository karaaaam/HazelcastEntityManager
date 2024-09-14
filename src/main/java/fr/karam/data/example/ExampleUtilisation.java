package fr.karam.data.example;

public class ExampleUtilisation {

    private final ExampleRepository repository;

    public ExampleUtilisation() {
        this.repository = new ExampleRepository();
    }

    public void testing(){
        ExampleObject exampleObject = repository.get();
        exampleObject.setName("aaa");
        exampleObject.getKeyWords().put("aa", "bb");

        //this method will only update cached object
        repository.set(exampleObject);

        //use this method if you want to store it (you need to chose a fetcherType and register it in HazelcastManager)
        repository.store(exampleObject);
    }
}
