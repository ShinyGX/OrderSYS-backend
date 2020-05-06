package com.order.sys.bean.model;

import javax.persistence.*;

@Entity
@Table(name = "com_business_window_link")
public class ComBusinessWindowLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "com_business_id")
    private Integer com_business_id;

    @Column(name = "com_business_windows_id")
    private Integer com_business_windows_id;

    public ComBusinessWindowLink() {
    }

    public ComBusinessWindowLink(Integer id, Integer com_business_id, Integer com_business_windows_id) {
        this.id = id;
        this.com_business_id = com_business_id;
        this.com_business_windows_id = com_business_windows_id;
    }

    public ComBusinessWindowLink(Integer com_business_id, Integer com_business_windows_id) {
        this.com_business_id = com_business_id;
        this.com_business_windows_id = com_business_windows_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCom_business_id() {
        return com_business_id;
    }

    public void setCom_business_id(Integer com_business_id) {
        this.com_business_id = com_business_id;
    }

    public Integer getCom_business_windows_id() {
        return com_business_windows_id;
    }

    public void setCom_business_windows_id(Integer com_business_windows_id) {
        this.com_business_windows_id = com_business_windows_id;
    }
}
