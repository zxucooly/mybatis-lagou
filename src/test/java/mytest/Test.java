package mytest;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.domain.blog.mappers.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;

public class Test extends BaseDataTest {
	private static SqlSessionFactory sessionFactory;

	@BeforeAll
	static void setup() throws Exception {
		createBlogDataSource();
		final String resource = "org/apache/ibatis/builder/MapperConfig.xml";
		final Reader reader = Resources.getResourceAsReader(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	@org.junit.jupiter.api.Test
	public void test1() {
		SqlSession sqlSession = sessionFactory.openSession();
		BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
		List<Map> maps =
				blogMapper.selectAllPosts();
		System.out.println(maps);
	}
}
