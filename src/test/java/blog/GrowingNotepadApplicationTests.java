package blog;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GrowingNotepadApplicationTests {
//
//	PostRepository postRepository = new PostRepository();
//
//	@AfterEach
//	void afterEach(){
//		postRepository.clear();
//	}
//
//	@Test
//	@DisplayName("메모저장")
//	public void save(){
//		//given
//		Memo memo = new Memo("Title-1", "본문내용1111", LocalDateTime.now());
//		//when
//		Memo saveMemo = postRepository.save(memo);
//		//then
//		Memo findMemo = postRepository.findById(memo.getId());
//		assertThat(findMemo).isEqualTo(saveMemo);
//	}
//
//	@Test
//	@DisplayName("메모목록조회")
//	public void findAll(){
//		//given
//		Memo memo1 = new Memo("Title-1", "본문내용1111", LocalDateTime.now());
//		Memo memo2 = new Memo("Title-2", "본문내용2222", LocalDateTime.now());
//		postRepository.save(memo1);
//		postRepository.save(memo2);
//		//when
//		List<Memo> memoList = postRepository.findAll();
//		//then
//		assertThat(memoList.size()).isEqualTo(2);
//		assertThat(memoList).contains(memo1,memo2);
//	}
//
//	@Test
//	@DisplayName("메모수정")
//	public void update(){
//		//given
//		Memo memo = new Memo("Title-1", "본문내용1111", LocalDateTime.now());
//		Memo saveMemo = postRepository.save(memo);
//		Long memoId = saveMemo.getId();
//		//when
//		Memo updateMemo = new Memo("Title-2", "본문내용2222", LocalDateTime.now());
//		postRepository.update(memoId,updateMemo);
//		Memo findMemo = postRepository.findById(memoId);
//		//then
//		assertThat(findMemo.getTitle()).isEqualTo(updateMemo.getTitle());
//		assertThat(findMemo.getText()).isEqualTo(updateMemo.getText());
//		assertThat(findMemo.getPostTime()).isEqualTo(updateMemo.getPostTime());
//	}
}
