package cn.com.editline;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AddHistoryTest.class, ClearHistoryTest.class,
		CurrentHistoryTest.class, HistoryGetTest.class,
		HistoryLengthTest.class, HistoryListTest.class,
		HistorySetPosTest.class, NextHistoryTest.class,
		PreviousHistoryTest.class, ReadlineTest.class, RemoveHistory.class,
		ReplaceHistoryEntryTest.class, WhereHistoryTest.class,
		WriteHistoryTest.class })
public class AllTests {

}
