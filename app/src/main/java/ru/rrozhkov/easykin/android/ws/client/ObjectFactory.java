
package ru.rrozhkov.easykin.android.ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.rrozhkov.easykin.android.ws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Tasks_QNAME = new QName("http://rrozhkov.ru/easykin", "tasks");
    private final static QName _Categories_QNAME = new QName("http://rrozhkov.ru/easykin", "categories");
    private final static QName _PersonsResponse_QNAME = new QName("http://rrozhkov.ru/easykin", "personsResponse");
    private final static QName _Persons_QNAME = new QName("http://rrozhkov.ru/easykin", "persons");
    private final static QName _CategoriesResponse_QNAME = new QName("http://rrozhkov.ru/easykin", "categoriesResponse");
    private final static QName _TasksResponse_QNAME = new QName("http://rrozhkov.ru/easykin", "tasksResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.rrozhkov.easykin.android.ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CategoryBean }
     * 
     */
    public CategoryBean createCategoryBean() {
        return new CategoryBean();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link CategoriesResponse }
     * 
     */
    public CategoriesResponse createCategoriesResponse() {
        return new CategoriesResponse();
    }

    /**
     * Create an instance of {@link PersonBean }
     * 
     */
    public PersonBean createPersonBean() {
        return new PersonBean();
    }

    /**
     * Create an instance of {@link Persons }
     * 
     */
    public Persons createPersons() {
        return new Persons();
    }

    /**
     * Create an instance of {@link TaskBean }
     * 
     */
    public TaskBean createTaskBean() {
        return new TaskBean();
    }

    /**
     * Create an instance of {@link Categories }
     * 
     */
    public Categories createCategories() {
        return new Categories();
    }

    /**
     * Create an instance of {@link Tasks }
     * 
     */
    public Tasks createTasks() {
        return new Tasks();
    }

    /**
     * Create an instance of {@link TasksResponse }
     * 
     */
    public TasksResponse createTasksResponse() {
        return new TasksResponse();
    }

    /**
     * Create an instance of {@link PersonsResponse }
     * 
     */
    public PersonsResponse createPersonsResponse() {
        return new PersonsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tasks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rrozhkov.ru/easykin", name = "tasks")
    public JAXBElement<Tasks> createTasks(Tasks value) {
        return new JAXBElement<Tasks>(_Tasks_QNAME, Tasks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Categories }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rrozhkov.ru/easykin", name = "categories")
    public JAXBElement<Categories> createCategories(Categories value) {
        return new JAXBElement<Categories>(_Categories_QNAME, Categories.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rrozhkov.ru/easykin", name = "personsResponse")
    public JAXBElement<PersonsResponse> createPersonsResponse(PersonsResponse value) {
        return new JAXBElement<PersonsResponse>(_PersonsResponse_QNAME, PersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Persons }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rrozhkov.ru/easykin", name = "persons")
    public JAXBElement<Persons> createPersons(Persons value) {
        return new JAXBElement<Persons>(_Persons_QNAME, Persons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rrozhkov.ru/easykin", name = "categoriesResponse")
    public JAXBElement<CategoriesResponse> createCategoriesResponse(CategoriesResponse value) {
        return new JAXBElement<CategoriesResponse>(_CategoriesResponse_QNAME, CategoriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TasksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rrozhkov.ru/easykin", name = "tasksResponse")
    public JAXBElement<TasksResponse> createTasksResponse(TasksResponse value) {
        return new JAXBElement<TasksResponse>(_TasksResponse_QNAME, TasksResponse.class, null, value);
    }

}
