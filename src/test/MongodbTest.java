import com.alibaba.fastjson.JSONObject;
import com.cybermkd.kit.MongoQuery;
import com.cybermkd.plugin.MongoJFinalPlugin;
import org.junit.Test;

/**
 * 创建人:T-baby
 * 创建日期: 16/4/15
 * 文件描述:
 */
public class MongodbTest {

    public void init() {

        MongoJFinalPlugin jFinalPlugin = new MongoJFinalPlugin();
        jFinalPlugin.add("127.0.0.1", 27017);
        jFinalPlugin.setDatabase("test");
        jFinalPlugin.start();

    }

    @Test
    public void testInsert() {

        init();
        MongoQuery query = new MongoQuery();
        AccountBean bean = new AccountBean();
        bean.setUsername("xxxx");
        bean.setPassword("aaaa");
        System.out.println(query.use("item").set(bean).save());

    }

    @Test
    public void testInsertList() {

        init();
        MongoQuery query = new MongoQuery();
        query.use("item").add(new MongoQuery().set("多子属性测试", "父").set("b", "1"))
                .add(new MongoQuery().set("多子属性测试", "子").set("b", "3"))
                .saveList();

    }

    @Test
    public void testFindById() {
        init();
        MongoQuery query = new MongoQuery();
        System.out.println(query.use("item").byId("5710a81ab73a87092e17a02b").find());
    }

    @Test
    public void testFindAll() {
        init();
        MongoQuery query = new MongoQuery();
        System.out.println(query.use("item").findAll());
    }

    @Test
    public void testFind() {
        init();
        MongoQuery query = new MongoQuery();
        System.out.println(JSONObject.toJSONString(query.use("item").eq("username", "xxxx").find(AccountBean.class)));
    }

    @Test
    public void testLike() {
        init();
        MongoQuery query = new MongoQuery();
        System.out.println(query.use("item").eq("a", "1").like("b", "a").find());
    }

    @Test
    public void testUpdate() {
        init();
        MongoQuery query = new MongoQuery();
        System.out.println(query.use("item").eq("username", "xxxx").modify("b", "3").update());
    }

    @Test
    public void testUpdateByObj() {
        init();
        MongoQuery query = new MongoQuery();
        AccountBean accountBean = new AccountBean();
        accountBean.setPassword("vvvv");
        System.out.println(query.use("item").eq("username", "xxxx").modify(accountBean).update());
    }

    @Test
    public void testUpById() {
        init();
        MongoQuery query = new MongoQuery();
        System.out.println(query.use("item").byId("5710a81ab73a87092e17a02b").modify("b", 4).update());
    }

    @Test
    public void testDel() {
        init();
        MongoQuery query = new MongoQuery();
        System.out.println(query.use("item").eq("test", "2").delete());
    }

    @Test
    public void testNotNull() {
        init();
        MongoQuery query = new MongoQuery();
        AccountBean bean = new AccountBean();
        System.out.println(bean.validation());
        System.out.println(bean.getErrorMessage());
    }
}

