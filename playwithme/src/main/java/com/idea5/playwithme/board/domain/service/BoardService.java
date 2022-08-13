package com.idea5.playwithme.board.domain.service;

import com.idea5.playwithme.board.domain.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

}
