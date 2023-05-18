package mini.project.pro.controller;


import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mini.project.pro.mapper.BoardMapper;
import mini.project.pro.model.Board;
import mini.project.pro.model.User;

@Controller
@RequestMapping("board")
public class BoardController {
    @Autowired
    BoardMapper boardMapper;

    @GetMapping("boardList") //게시글 목록 조회
    public String boardList(HttpSession session, Model model){
        //DB에서 게시글 목록을 전부 가져와서 model에 담아준다.
        ArrayList<Board> boardList = boardMapper.boardList();
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    @GetMapping("boardCreate") //게시글 작성 페이지 
    public String boardCreate(){
        return "board/boardCreate";
    }

    @PostMapping("boardCreate") //게시글 작성 요청
    public String boardCreate(HttpSession session, Board board){
        User user = (User) session.getAttribute("user");
        board.setBoardWriter(user.getUserId());
        boardMapper.boardCreate(board);
        return "redirect:/board/boardList";
    }

    @GetMapping("boardDetail") //게시글 상세보기
    public String boardDetail(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
         ArrayList<Board> boardList = boardMapper.boardList();
         for(Board board : boardList){
            if(board.getBoardNo() == boardNo){
                model.addAttribute("board", board);
            }
         }
        return "board/boardDetail";
    }

    @GetMapping("boardUpdate")
    public String boardupdate(HttpSession session, @RequestParam("boardNo") int boardNo , Model model){
        //수정하고자 하는 페이지의 글정보를 가져온다.
        //boardCreate페이지를 참고 하는데
        //textArear 태그 안에 글정보가 있어야 하겠다.
        ArrayList<Board> boardList = boardMapper.boardList();
         for(Board board : boardList){
            if(board.getBoardNo() == boardNo){
                model.addAttribute("board", board);
            }
         }
        return "board/boardUpdate";
    }

    @PostMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Board board){
        boardMapper.boardUpdate(board);
        return "redirect:/board/boardList";
    }

    @GetMapping("boardRemove")
    public String boardRemove(@RequestParam("boardNo") int boardNo){
        boardMapper.boardRemove(boardNo);
        return "redirect:/board/boardList";
    }
}
