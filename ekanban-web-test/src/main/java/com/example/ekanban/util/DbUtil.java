package com.example.ekanban.util;

import com.example.ekanban.dto.FlowCsv;
import com.example.ekanban.dto.StepCsv;
import com.example.ekanban.dto.TestCsv;
import com.example.ekanban.entity.*;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import jdk.nashorn.internal.runtime.logging.Loggable;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mdzahidraza on 03/06/17.
 */
public class DbUtil {

    private static final Logger logger = LoggerFactory.getLogger(DbUtil.class);

    private static String filename = MiscUtil.getEkanbanHome() + File.separator + "data" + File.separator + "test" + File.separator + "TestSet1.xlsx";

    public static boolean initialize() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        List<String> list = new ArrayList<>();
        list.add("dozer_mapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        String csvOutput = null;
        CSVReader reader = null;

        /*//////  Initializing Flow  ///////////*/
        logger.info("initializing FlowCsv...");
        csvOutput = ExcelUtil.getCsvFromXlsx(new File(filename),0);
        reader = new CSVReader(new StringReader(csvOutput), ';');
        HeaderColumnNameMappingStrategy<FlowCsv> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(FlowCsv.class);
        CsvToBean<FlowCsv> csvToBean = new CsvToBean<>();

        List<FlowCsv> flowList = csvToBean.parse(strategy, reader);
        flowList = flowList.stream().filter(flowCsv -> flowCsv.getId() != null).collect(Collectors.toList());
        entityManager.getTransaction().begin();
        flowList.forEach(flow -> {
            entityManager.persist(mapper.map(flow, Flow.class));
        });
        entityManager.getTransaction().commit();
        logger.info("initialized flow values.");


        /*//////  Initializing Test  ///////////*/
        logger.info("initializing TestCsv...");
        csvOutput = ExcelUtil.getCsvFromXlsx(new File(filename),1);
        reader = new CSVReader(new StringReader(csvOutput), ';');
        HeaderColumnNameMappingStrategy<TestCsv> strategy2 = new HeaderColumnNameMappingStrategy<>();
        strategy2.setType(TestCsv.class);
        CsvToBean<TestCsv> csvToBean2 = new CsvToBean<>();

        List<TestCsv> testList = csvToBean2.parse(strategy2, reader);
        testList = testList.stream().filter(testCsv -> testCsv.getId() != null).collect(Collectors.toList());
        entityManager.getTransaction().begin();

        testList.forEach(testCsv -> {
            Test test = mapper.map(testCsv,Test.class);
            test.setFlow(new Flow(testCsv.getFlowId().longValue()));
            entityManager.persist(test);
        });
        entityManager.getTransaction().commit();
        logger.info("initialized test values.");


        /*//////  Initializing Step  ///////////*/
        logger.info("initializing steps...");
        csvOutput = ExcelUtil.getCsvFromXlsx(new File(filename),2);
        reader = new CSVReader(new StringReader(csvOutput), ';');
        HeaderColumnNameMappingStrategy<StepCsv> strategy3 = new HeaderColumnNameMappingStrategy<>();
        strategy3.setType(StepCsv.class);
        CsvToBean<StepCsv> csvToBean3 = new CsvToBean<>();

        List<StepCsv> stepList = csvToBean3.parse(strategy3, reader);
        stepList = stepList.stream().filter(stepCsv -> stepCsv.getTestId() != null).collect(Collectors.toList());
        entityManager.getTransaction().begin();
        stepList.forEach(stepCsv -> {
            System.out.println(stepCsv);
            Step step = mapper.map(stepCsv, Step.class);
            step.setTest(new Test(stepCsv.getTestId().longValue()));
            entityManager.persist(step);
        });
        entityManager.getTransaction().commit();
        logger.info("initialized step values.");

        return true;
    }

    public static List<Flow> getAllFlow() {
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Flow> query = builder.createQuery(Flow.class);
        Root<Flow> flow = query.from(Flow.class);
        return em.createQuery(query).getResultList();
    }

    public static List<Test> getAllTestForFlow(Flow flow) {
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Test> query = builder.createQuery(Test.class);
        Root<Test> test = query.from(Test.class);

        ParameterExpression<Flow> flowParam = builder.parameter(Flow.class);

        query.select(test).where(builder.equal(test.get(Test_.flow), flowParam));

        return em.createQuery(query)
                .setParameter(flowParam, flow)
                .getResultList();
    }

    public static List<Step> getAllStepsForTest(Test test) {
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Step> query = builder.createQuery(Step.class);
        Root<Step> step = query.from(Step.class);

        ParameterExpression<Test> testParam = builder.parameter(Test.class);

        query.select(step).where(builder.equal(step.get(Step_.test), testParam));

        return em.createQuery(query)
                .setParameter(testParam, test)
                .getResultList();
    }


}
