package entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * BaseUser generated by hbm2java
 */
@Entity
@Table(name = "sys_group", uniqueConstraints = {@UniqueConstraint(columnNames = "name") })
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "id"))
    ,
    @AttributeOverride(name = "create_uid", column = @Column(name = "create_uid"))
    ,
    @AttributeOverride(name = "update_uid", column = @Column(name = "update_uid"))
    ,
    @AttributeOverride(name = "create_time", column = @Column(name = "create_time"))
    ,
    @AttributeOverride(name = "update_time", column = @Column(name = "update_time"))
})
public class SysGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_seq")
    @SequenceGenerator(name = "group_id_seq", sequenceName = "group_id_seq", allocationSize = 1)
    private Integer id;
    
    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private SysGroup parent;
    
    @Column(name = "name")
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_group_entity_perm", joinColumns = {
			@JoinColumn(name = "group_id", updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "perm_id",
					 updatable = false) })
    private Set<SysEntityPermission> entityPermissions = new HashSet<SysEntityPermission>(0);
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_group_view_perm", joinColumns = {
			@JoinColumn(name = "group_id", updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "view_id",
					 updatable = false) })
    private Set<SysView> viewPermissions = new HashSet<SysView>(0);
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_group_menu_perm", joinColumns = {
			@JoinColumn(name = "group_id",  updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "menu_id",
					 updatable = false) })
    private Set<SysMenu> menuPermissions = new HashSet<SysMenu>(0);
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_group", joinColumns = {
			@JoinColumn(name = "group_id",  updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "user_id",
					 updatable = false) })
    private Set<BaseUser> users = new HashSet<BaseUser>(0);
    
    public SysGroup() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public SysGroup(String name) {
        this.name = name;
    }

    public SysGroup getParent() {
        return parent;
    }

    public void setParent(SysGroup parent) {
        this.parent = parent;
    }

    public Set<SysEntityPermission> getEntityPermissions() {
        return entityPermissions;
    }

    public void setEntityPermissions(Set<SysEntityPermission> entityPermissions) {
        this.entityPermissions = entityPermissions;
    }

    public Set<SysView> getViewPermissions() {
        return viewPermissions;
    }

    public void setViewPermissions(Set<SysView> viewPermissions) {
        this.viewPermissions = viewPermissions;
    }

    public Set<SysMenu> getMenuPermissions() {
        return menuPermissions;
    }

    public void setMenuPermissions(Set<SysMenu> menuPermissions) {
        this.menuPermissions = menuPermissions;
    }
    
    public Set<BaseUser> getUsers() {
        return users;
    }

    public void setUsers(Set<BaseUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SysGroup{id = "+ id + ", parent=" + parent + ", name=" + name + '}';
    }
    
}