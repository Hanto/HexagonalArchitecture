package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 19/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.AccountServiceI;
import com.myrran.cleanarchitecture.account.application.ports.ParallelProcessing;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WebAdapter.class)
public class WebAdapterTest
{
    @Autowired private MockMvc mockMvc;
    @MockBean private ParallelProcessing parallelProcessing;
    @MockBean private AccountServiceI accountService;
    @MockBean private RestTemplate restTemplate;
    @MockBean private ActivityDTOAssembler activityAssembler;
    @MockBean private AccountDTOAssembler accountAssembler;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    //@Test
    void testSendMoney() throws Exception
    {
        MockHttpServletRequestBuilder post = post("/api/send")
            .param("sourceAccountId", "1")
            .param("targetAccountId", "2")
            .param("amount", "100");

        mockMvc.perform(post)
            .andExpect(status().isOk());

        BDDMockito.then(parallelProcessing)
            .should().processMoneyOrder(1, 2, BigDecimal.valueOf(100));
    }
}