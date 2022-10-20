package com.tes.Penjualan.service;

import com.tes.Penjualan.dao.AccountRepository;
import com.tes.Penjualan.dao.ProductRepository;
import com.tes.Penjualan.dao.TransDetailRepository;
import com.tes.Penjualan.dao.TransRepository;
import com.tes.Penjualan.dto.product.ProductGridDTO;
import com.tes.Penjualan.dto.transaction.InsertTransactionDTO;
import com.tes.Penjualan.dto.transaction.TransactionDTO;
import com.tes.Penjualan.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransServiceImpl implements TransService {

    @Autowired
    private TransRepository transRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransDetailRepository transDetailRepository;

    private final int rowsInPage = 6;

    @Override
    public void buyProduct(InsertTransactionDTO dto, String username) {
        Product findProduct = productRepository.findById(dto.getProductCode()).get();
        BigDecimal harga = findProduct.getPrice()
                            .subtract( (findProduct.getDiscount())
                                        .divide(new BigDecimal(100))
                                        .multiply(findProduct.getPrice())
                            );
        BigDecimal total = (harga).multiply(new BigDecimal(dto.getQuantity()));

        Transaction temp = new Transaction(
                dto.getTransactionId(),
                username,
                findProduct,
                dto.getQuantity(),
                total
        );

        System.out.println("[BuyProduct : ] "+temp);
        transRepository.save(temp);
    }

    @Override
    public List<TransactionDTO> getCart(Integer page, String username) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("transactionId"));
        List<TransactionDTO> cart = transRepository.findAllCart(username, pagination);
        return cart;
    }

    public String getDocumentNumber() {
        String lastNumber = productRepository.getHighestNumber();
        String docNumber = "";

        if (lastNumber == null){
            docNumber = "001";
        } else {
            int number = Integer.parseInt(lastNumber) + 1;

            if (number <= 9){
                docNumber = "00"+number;
            }
            else if(number <= 99){
                docNumber = "0"+number;
            } else {
                docNumber = Integer.toString(number);
            }
        }
        return docNumber;
    }

    @Override
    public void updateTransaction(TransactionDTO dto, String username, BigDecimal total) {
        System.out.println("[Total Price : ] "+total);

        String docNumber = getDocumentNumber();
        System.out.println("[docNumber ] "+docNumber);

        TransHeaderId headerId = new TransHeaderId(
                "TRX",
                docNumber
        );

        TransactionDetail transDetail = new TransactionDetail(
                headerId,
                total,
                LocalDate.now()
        );
        TransactionDetail newTransDetail = transDetailRepository.save(transDetail);

        List<Transaction> findTrans = transRepository.findByUsername(username);
        for (Transaction oneTrans : findTrans) {
            oneTrans.setTransDetail(newTransDetail);
            transRepository.save(oneTrans);
        }
    }

    @Override
    public Long getTotalPages(String username) {
        double totalData = (double)(transRepository.countPages(username));
        long totalPage = (long)(Math.ceil(totalData / this.rowsInPage));
        return totalPage;
    }


}
