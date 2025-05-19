package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.RoomDto;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomService;

import jakarta.validation.Valid;

/**
* Method URI            功能
* --------------------------------------------------------------------
* GET    /rooms                查詢所有會議室(多筆)
* GET    /room/{roomId}        查詢指定會議室(單筆)- 修改專用
* POST   /room                 新增會議室
* POST ->PUT   /room/update/{roomId} 完整修改會議室(同時修改 roomName 與 roomSize)
* GET -> DELETE    /room/delete/{roomId} 刪除會議室
* --------------------------------------------------------------------
* */

@Controller
@RequestMapping(value = {"/room", "/rooms"})
public class RoomController {

    private final RoomRepository roomRepository;
	
	@Autowired
	private RoomService roomService;


    RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
	
	
	@GetMapping  //進階簡化語法
	public String getRoom(Model model, @ModelAttribute RoomDto roomDto) {  //居然還可以再省略@？
	  //RoomDto roomDto = new RoomDto();  若在jsp內有 modelAttribute 他就可以移到上面的參數
		List<RoomDto> roomDtos = roomService.findAllRooms();	
	  //model.addAttribute("roomDto", roomDto);  這行的路徑也就被取代可以被簡化
		model.addAttribute("roomDtos", roomDtos);
		return "room/room";
	}
	
	@GetMapping("/{roomId}")
	public String getRoom(@PathVariable Integer roomId, Model model) {
		RoomDto roomDto = roomService.getRoomById(roomId);
		model.addAttribute("roomDto", roomDto);
		return "room/room_update";
	}
	
//	@GetMapping  //初學詳細寫法
//	public String getRoom2(Model model) {
//		RoomDto roomDto = new RoomDto();
//		List<RoomDto> roomDtos = roomService.findAllRooms();
//		model.addAttribute("roomDto", roomDto);
//		model.addAttribute("roomDtos", roomDtos);
//		return "room/room";
//	}
	
	//@Valid RoomDto roomdto, BindingResult bindingResult
	//RoomDto 要進行屬性資料驗證，驗證結果放到 bindingResult
	
	@PostMapping           //@ModelAttribute 目前在這裡有無都可以新增，
	public String adddRoom(@Valid RoomDto roomDto, BindingResult bindingResult, Model model) {
		//驗證資料
		if(bindingResult.hasErrors()) {
			model.addAttribute("roomDtos", roomService.findAllRooms());
			return "room/room";
		}
		//進行新增
		roomService.addRoom(roomDto);
		return "redirect:/rooms";
	}
	
	@PutMapping("/update/{roomId}")
	public String updateRoom(@PathVariable Integer roomId,  @Valid RoomDto roomDto, BindingResult bindingResult) {
		//驗證資料
		if(bindingResult.hasErrors()) {					
			return "room/room_update";
		}
		//進行修改
		roomService.updateRoom(roomId, roomDto);
		return "redirect:/rooms";
	}
	
	@DeleteMapping("/delete/{roomId}")
	public String deleteRoom(@PathVariable Integer roomId) {
		roomService.deleteRoom(roomId);
		return "redirect:/rooms";
	}
	
	@ExceptionHandler({Exception.class})
	public String handleException(Exception e, Model model) {
		e.printStackTrace();
		model.addAttribute("message", e.getMessage());
		return "error";
	}

}
