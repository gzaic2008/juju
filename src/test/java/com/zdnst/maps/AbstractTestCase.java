package com.zdnst.maps;

import org.junit.runner.RunWith;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("deprecation")
@Transactional
//事务控制， 自动rollback污染数据库
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:conf/applicationContext.xml" })
public abstract class AbstractTestCase extends
		AbstractTransactionalDataSourceSpringContextTests {

}
