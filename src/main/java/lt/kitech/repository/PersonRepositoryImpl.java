package lt.kitech.repository;

import lt.kitech.model.Person;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PersonRepositoryImpl extends BasicDAO<Person, ObjectId> implements PersonRepository {

    private static final int DEFAULT_LIMIT = 50;

    @Autowired
    public PersonRepositoryImpl(Datastore datastore) {
        super(datastore);
    }

    public List<Person> findByName(String name) {
        Query<Person> query = createQuery();
        query.field(QueryParam.NAME.getParam()).equal(name);
        return find(query).asList();
    }

    public List<Person> findByStreet(String street) {
        Query<Person> query = createQuery();
        query.field(QueryParam.ADDRESS_STREET.getParam()).equal(street);
        return find(query.limit(DEFAULT_LIMIT)).asList();
    }

    public List<Person> findByNameAndStreet(String name, String street) {
        Query<Person> query = createQuery();
        query.and(
                query.criteria(QueryParam.NAME.getParam()).equal(name),
                query.criteria(QueryParam.ADDRESS_STREET.getParam()).equal(street));
        return find(query).asList();
    }

    enum QueryParam {
        NAME("name"),
        AGE("age"),
        ADDRESS("address"),
        ADDRESS_STREET("address.street"),
        LAST_NAME("lastName");

        private String param;
        QueryParam(String param) {
            this.param = param;
        }

        public String getParam() {
            return param;
        }
    }
}
